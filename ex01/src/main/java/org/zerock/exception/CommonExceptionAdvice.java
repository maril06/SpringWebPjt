package org.zerock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

/*  AOP(aspect-Oriented-Programming) : 프로그램에서 필요한 공통적인 관심사를 정리 하자는 개념.
 *  Controller를 작성할 때 메서드의 모든 예외사항을 전부 핸들링 해야 하면, 중복적이고 많은 양의 코드를 작성해야함.
 *  따라서 공통적인 예외사항에 대해서는 별도로 @ControllerAdvice를 이용해서 분리함.
 * 
 * */


/*@ControllerAdvice : 해당 객체가 스프링의 컨트롤러에서 발생하는 예외를 처리하는 존재임을 명시.*/
@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	/*해당 메서드가 모든 예외처리(Exception.class)를 한다는 것을 의미.
	 * 
	 * 특정한 타입의 예외를 처리하고 싶다면 구체적인 예외로 클래스를 지정하면 됨.
	 * */
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		
		log.error("Exception ................" + ex.getMessage());
		model.addAttribute("exception", ex); 
		/*ex객체를 exception이라는 이름으로 추가함. 뷰에서는 exception이름을 통해서 value를 사용할 수 있음.*/
		log.error(model);
		
		/*return값이 문자열이므로 Jsp 파일의 경로가 됨. /WEB-INF/views/error_page.jsp*/
		return "error_page";
		
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException ex) {
		
		return "custom404";
	}
	
	
	
	
}
