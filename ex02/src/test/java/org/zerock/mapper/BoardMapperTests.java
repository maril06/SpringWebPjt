package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTests {

	@Setter(onMethod_ = { @Autowired})
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//		
//		mapper.getList().forEach(board->log.info(board));
//	}
	
//	@Test
//	public void testInsert() {
//		
//		BoardVO board = new BoardVO();
//		
//		board.setTitle("테스트 작성 글");
//		board.setContent("테스트 작성 내용");
//		board.setWriter("테스트 작성자");
//		
//		mapper.insert(board);
//		
//		// Lombok이 만들어주는 toString()을 이용해서 bno멤버변수의 값을 알아보기 위함.
//		// BoardVO(bno=null, title=테스트 작성 글, content=테스트 작성 내용, writer=테스트 작성자, regdate=null, updateDate=null)
//		// bno 값이 null인것을 확인할수 있다. 결론적으로 bno(PK) 값을 확인할 수 없다.
//		log.info(board);
//	}
	
//	@Test
//	public void testInsertSelectKey() {
//		
//		BoardVO board = new BoardVO();
//		board.setTitle("테스트 작성 글(selectKey)");
//		board.setContent("테스트 작성 내용(selectKey)");
//		board.setWriter("테스트 작성자(selectKey)");
//		
//		
//		
//		//BoardVO(bno=22, title=테스트 작성 글(selectKey), content=테스트 작성 내용(selectKey), writer=테스트 작성자(selectKey), regdate=null, updateDate=null)
//		// @SelectKey를 이용하는 방식은 Sql을 한번 더 실행해서 부담이 있긴하지만,
//		// 자동으로 처리되는 PK값을 확인해야하는 상황에서 유용하게 사용됨.
//		mapper.insertSelectKey(board);
//		
//		log.info(board);
//	}
	
	@Test
	public void testRead() {
		
		//존재하는 게시물 번호로 테스트
		BoardVO board = mapper.read(22L);
		
		
		//.BoardVO(bno=22, title=테스트 작성 글(selectKey), content=테스트 작성 내용(selectKey), writer=테스트 작성자(selectKey), regdate=Thu Mar 23 20:14:33 JST 2023, updateDate=Thu Mar 23 20:14:33 JST 2023)
		log.info(board);
	}
}
