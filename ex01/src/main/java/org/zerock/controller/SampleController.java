package org.zerock.controller;

import java.util.ArrayList;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	
	/**********************************************************************************************************************
	 *  !!!!! Controller의 메서드를 작성할 때는 특별하게 Model이라는 타입을 파라미터로 지정할 수 있음!!!!!!!!!
	 *  Model 객체는 JSP에 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할을 하는 존재임!!! 
	 *  (((JSP와 같이 뷰로 전달해야 하는 데이터를 담아서 보낼 수 있음
	 * 
	 * ===> 메서드의 파라미터를 Model 타입으로 선언하게 되면, 자동으로 Spirng MVC에서 
	 *          Model타입의 객체를 만들어 주기 때문에, 개발자 입장에서 필요한 데이터를 담아주는 작업만 하면 됨.
	 * 
	 * 기본적으로 Java Beans 규칙에 맞는 객체는 다시 화면으로 객체를 전달함.
	 * Java Beans 규칙 : 단순히 생성자가 없거나 빈생성자를 가져야하며, getter/setter를 가진 클래스의 객체
	 * SampleDTO 같은 경우 Java Bean의 규칙에 맞기 떄문에 다시 화면까지 전달됨.
	 * 전달 될 때, 클래스명의 앞글자는 소문자로 처리됨.
	 * !!! 반면 기본 자료형의 경우 파라미터로 선언하더라도 기본적으로 화면까지 전달되지는 않음.(따로 Model에 셋팅해서 보내야함)
	 * ********************************************************************************************************************/
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, int page) {
		
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		
		/*<beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
				<beans:property name="prefix" value="/WEB-INF/views/" />
				<beans:property name="suffix" value=".jsp" />
		  </beans:bean>
		 * servlet-context.xml 에서 정의된 ViewResolver에 의해 views 아래에 있는 sample 아래에 있는 ex04.jsp로 전달됨.
		 * */
		return "/sample/ex04";
	}
	
	
	/***********************************************************************************/
	/* @ModelAttribute는 강제로, 전달받은 파라미터를 Model에 담아서 전달할 때 필요한 어노테이션이다.
	 *  @ModelAttribute가 걸린 파라미터는 타입에 관계없이 무조건 Model에 담아서 전달됨.
	 *  http://localhost:8080/sample/ex04_1?name=aaa&age=11&page=9
	 *  
	 *  SampleDTO SampleDTO(name=aaa, age=11)
		PAGE 9
		
		페이지도 화면에 전달된걸 확인할 수 있다.
	 *  
	 */
	@GetMapping("/ex04_1")
	public String ex04_1(SampleDTO dto, @ModelAttribute("page") int page) {
		
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "/sample/ex04";
	}
	
	/******************************************************************************************************************************************************************/
	/*		Controller의 리턴 타입.
	 * 
	 ******************************************************************************************************************************************************************/
	/* 1. void 타입
	 *   => 해당 URL의 경로를 그대로 JSP 파일의 이름으로 사용한다. => ex05.jsp로 이동
	 * */
	@GetMapping("/ex05")
	public void ex05() {
		
		log.info("/ex05......................................................................................................................");
		
	}
	
	/* 2. String 타입
	 *   => 상황에 따라 다른 화면을 보여줄 필요가 있을 경우 유용하게 사용됨.
	 * */
	
	/* 3. 객체 타입
	 *   => 리턴 타입을 VO, DTO등의 객체 타입으로 지정하면 주로 JSON 데이터를 만들어 내는 용도로 사용한다.
	 *   pom.xml에 jackson-databind 추가해야함.
	 *   스프링 MVC가 자동으로 Json타입으로 객체를 변환해서 전달해 준다.
	 *   
	 *   {
			"name": "홍길동",
			"age": 10
		  }
	 * */
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		
		log.info("/ex06......................................................................................................................");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		
		return dto;
	}
	
	/* ResponseEntity를 통해서 원하는 헤더 정보나 데이터를 전달 할 수 있다.
	 * => HttpHeaders 객체를 같이 전달 할 수 있고, 원하는 HTTP 헤더 메시지를 가공하는 것이 가능함.
	 * HTTP 헤더의 역할
	 * HTTP 헤더는 클라이언트와 서버가 요청 또는 응답으로 부가적인 정보를 전송할 수 있도록 해줍니다.
	 * 헤더?
	 * 저장 되거나 전송되는 데이터 블록의 맨 앞에 위치한 데이터를 가리킵니다.
		특정 프로토콜의 헤더의 내용은 특정 프로토콜의 기능을 제공하기 위한 정보를 담고있습니다.
		헤더의 뒤에 이어지는 데이터는 페이로드 혹은 바디로 불립니다.
	 * */
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		
		log.info("/ex07......................................................................................................................");
		
		String msg = "{\" name\": \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	/*void타입이기 때문에 url주소와 같은 jsp파일로 자동으로 리턴한다.*/
	@GetMapping("/exUpload")
	public void exUpload() {
		
		log.info("/exUpload......................................................................................................................");
		
	}
	
	/*
	 *  스프링 MVC는 전달되는 파라미터가 동일한 이름으로 여러개 존재하면 배열로 처리 가능..!!!
	 *  MultipartFile의 배열타입으로 작성.
	 * 
	 *  INFO : org.zerock.controller.SampleController - ------------------------------------------------------------------
		INFO : org.zerock.controller.SampleController - name : 춘식이.png
		INFO : org.zerock.controller.SampleController - size : 266734
		INFO : org.zerock.controller.SampleController - ------------------------------------------------------------------
		INFO : org.zerock.controller.SampleController - name : 스크린샷_20230125_105657.png
		INFO : org.zerock.controller.SampleController - size : 117504
		INFO : org.zerock.controller.SampleController - ------------------------------------------------------------------
		INFO : org.zerock.controller.SampleController - name : 스크린샷_20230209_070431.png
		INFO : org.zerock.controller.SampleController - size : 52440
		INFO : org.zerock.controller.SampleController - ------------------------------------------------------------------
		INFO : org.zerock.controller.SampleController - name : 
		INFO : org.zerock.controller.SampleController - size : 0
	 * 
	 * */
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file -> {
			log.info("------------------------------------------------------------------");
			log.info("name : " + file.getOriginalFilename());
			log.info("size : " + file.getSize());
		});
	}
	
	
	
	
	
}
