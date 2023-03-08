package org.zerock.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/* 기존 프로젝트에서는 web.xml을 이용해서 스프링을 구동시킴.
 * xml을 사용하지 않은 경우에는 이 역할을 대신하는 클래스를 작성해서 처리한다.
 * */
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	
	/*
	 * 3개의 추상 메서드를 오버라이드 하도록 장선한다.
	 * getRootConfig는 root-context.xml을 대신하는 클래스를 지정한다.
	 * 
	 * */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		/*SpringMVC의 기본 경로를 '/'로 변경
		 * 
		 * 
		 * */
		return new String[] {"/"};
	}
	
	
}
