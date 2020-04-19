package br.com.cdi.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationScopedBean {

	private Bean bean;

	@PostConstruct
	public void init() {
		if (bean == null) {
			bean = new Bean();
			bean.setName("CDI");
		}
	}

	public Bean getBean() {
		return bean;
	}

}
