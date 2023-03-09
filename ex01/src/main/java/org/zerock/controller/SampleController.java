package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

/* servlet-context.xml에 <context:component-scan base-package="org.zerock.controller" /> 가 있기때문에,
 * @Controller를 사용하면 SampleController는 자동으로 빈으로 등록된다.
 * 
 * @RequestMapping은 현재 클래스의 모든 메서드들의 기본적인 URL 경로를 지정한다.
 * "/sampe/* 이라는 경로로 오는 URL은 모두 SampleController에서 처리됨.
 *  클래스의 선언과 메서드의 선언에 사용할 수 있다.
 * */

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@RequestMapping("")
	public void basic() {
		
		log.info("basic........");
	}
	
	/*RequestMapping은 GET, POST 방식 모두를 지원해야 하는 경우 배열로 처리해서 지정가능함.*/
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		
		log.info("basic get...................................");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic Only  get...................................");
	}
	
	
	//http://localhost:8080/sample/ex01?name=AAA&age=10
	//파라미터의 타입에 따라 자동으로 변환하는 방식을 이용한다. AAA는 String, 10은 Int로 받음.
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		
		log.info("" + dto);
		
		return "ex01";
	}
	//만일, 기본 자료형이나 문자열 등을 이용한다면 파라미터의 타입만을 맞게 선언해주는 방식도 사용할 수 있다.
	//http://localhost:8080/sample/ex02?name=AAA&age=10
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		
		log.info("name :  " + name);
		log.info("age :  " + age);
		
		return "ex02";
	}
	
	/****리스트, 배열 처리*****/
	
	//동일한 이름의 파라미터가 여러개 전달되는 경우 ArrayList<>등을 이용해서 처리 가능
	// ids라는 이름의 파라미터가 여러개 전달되더라도 ArrayList<String>이 생성되어 자동으로 수집됨.
	//http://localhost:8080/sample/ex02List?ids=111&ids=222&ids=333
	//INFO : org.zerock.controller.SampleController - ids : [111, 222, 333]
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		
		log.info("ids : " + ids);
		
		return "ex02List";
		
	}
	
	//http://localhost:8080/sample/ex02Array?ids=111&ids=222&ids=333
	//INFO : org.zerock.controller.SampleController - array ids : [111, 222, 333]
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids")String[] ids) {
		
		log.info("array ids : " + Arrays.toString(ids));
		
		return "ex02Array";
		
	}
	
	/************************************/
	/* 객체 리스트
	 * -> 전달하는 데이터가 SampleDTO와 같이 객체 타입이고, 여러개를 처리해야 한다면
	 *  SampleDTO의 리스트를 포함하는 SampleDTOList 클래스를 설계한다.
	 * http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb
	 * INFO : org.zerock.controller.SampleController - list dtos : SampleDTOList(list=[SampleDTO(name=aaa, age=0), SampleDTO(name=bbb, age=0)])
	 **************************************/
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos : " + list);
		return "ex02Bean";
	}
	
	/**************************************************************/
	/*파라미터의 수집을 다른 용어로 binding(바인딩)이라고 함.
	 * 변환이 가능한 데이터는 자동으로 변환되지만, 화면에서 '2018-01-01'와 같이 문자열로 전달된 데이터를
	 * Date 타입으로 변환하는 작업을 할 때에는,
	 * @InitBinder를 이용해서 처리할 수 있다.
	 ***************************************************************/
	/*
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	*/
	//http://localhost:8080/sample/ex03?title=test&dueDate=2018-01-01
	//INFO : org.zerock.controller.SampleController - todo   : TodoDTO(title=test, dueDate=Mon Jan 01 00:00:00 JST 2018)
	@GetMapping("ex03")
	public String ex03(TodoDTO todo) {
		
		log.info("todo   : " + todo);
		return "ex03";
	}
	
	/* @DateTimeFormat을 이용한 날짜 변환
	 * =>파라미터로 사용되는 인스턴스 변수에 @DateTimeFormat을 적용해도 변환 가능.
	 * 이 경우 @InitBinder는 필요하지 않음.
	 * @DateTimeFormat(pattern="yyyy/MM/dd")
		private Date dueDate;
	 * 
	 * 문자열로 'yyyy/MM/dd의 형식이 맞으면 자동으로 날짜 타입으로 변환됨.
	 * http://localhost:8080/sample/ex03?title=test&dueDate=2018/01/01
	 * TodoDTO(title=test, dueDate=Mon Jan 01 00:00:00 JST 2018)
	 * */
	
	
	
	
}
