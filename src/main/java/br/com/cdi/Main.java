package br.com.cdi;

import java.io.File;
import java.util.logging.Logger;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.jboss.weld.environment.servlet.Listener;

public class Main {

	public static void main(String[] args) throws Exception {

		Logger logger = Logger.getLogger(Main.class.getName());

		String webappDirLocation = "src/main/webapp/";
		Tomcat tomcat = new Tomcat();

		setPort(tomcat);

		StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
		logger.info("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());

		File additionWebInfClasses = new File("target/classes");
		WebResourceRoot resources = new StandardRoot(ctx);

		resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
		ctx.setResources(resources);

		Tomcat.addServlet(ctx, "jersey-container-servlet", resourceConfig()); // Aplicando Jersey Filter

		ctx.addApplicationListener(Listener.class.getName()); // Aplicando WELD Listner

		tomcat.getConnector();

		tomcat.getHost().setAutoDeploy(true);

		tomcat.start();
		tomcat.getServer().await();
	}

	private static void setPort(Tomcat tomcat) {
		String webPort = System.getenv("PORT");
		if (webPort == null || webPort.isEmpty()) {
			webPort = "8080";
		}

		tomcat.setPort(Integer.valueOf(webPort));
	}

	private static ServletContainer resourceConfig() {
		return new ServletContainer(new ResourceConfig(new ResourceLoader().getClasses()));
	}
}
