package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {
	
//	@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	
	//id속성 값은 메서드와 일치해야 한다. 
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	//등록, 삭제, 수정과 같은 DML 작업은 몇건의 데이터가
   // 처리 되었는지를 반환할 수 있다.
	public int delete(Long bno);
	
	//업데이트 역시 몇개의 데이터가 수정되었는가를 반환할 수 있다.
	public int update(BoardVO board);
}
