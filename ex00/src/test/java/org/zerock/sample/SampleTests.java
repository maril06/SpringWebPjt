package org.zerock.sample;
import static org.junit.Assert.assertNotNull;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/* spring-test 모듈을 이용해서 간단하게 스프링을 가동시킴.
 * 
 * */

/*@Runwith : 현재 테스트 코드가 스프링을 실행하는 역할을 할것이라는 것을 표시
 *@ContextConfiguration : 스프링 내에 객체로 등록함.
 *@Log4j : Lombok을 이용해서 로그를 기록하는 Logger를 변수로 생성함. 
 * */

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class SampleTests {
	
	@Setter(onMethod_ = {@Autowired})
	private Restaurant restaurant;
	
	/*JUnit에서 테스트 대상임을 표시하는 어노테이션*/
	@Test
	public void testExist() {
		
		/*restaurant 변수가 null이 아니어야만 테스트가 성공한다는 것을 의미함.*/
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("===================================");
		log.info(restaurant.getChef());
	}
}
