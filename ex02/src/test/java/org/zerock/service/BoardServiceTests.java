package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	// 임포트 순서 -> autowired 먼저하고 setter를 해야 오류 안남.
	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
	//@Test
	public void testExist() {
		log.info(service);
		
		assertNotNull(service);
	}
	
	//@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("select key 새로 작성 글");
		board.setContent("select key 새로 작성된 내용");
		board.setWriter("newbie2");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호 : " + board.getBno());
	}
	
	//@Test
	public void testGetList() {
		
		service.getList().forEach(board -> log.info(board));
	}
	
	//@Test
	public void testGet() {
		
		log.info(service.get(1L));
	}
	
	//@Test
	public void testDelete() {
		
		log.info("REMOVE RESULT : " + service.remove(2L));
	}
	
	//@Test
	public void testUpdate() {
		
		BoardVO board = service.get(1L);
		
		if(board == null) {
			return;
		}
		
		board.setTitle("제목을 수정합니다");
		log.info("MODIFY RESULT : " + service.modify(board));
	}
}
