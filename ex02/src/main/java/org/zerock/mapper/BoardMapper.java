package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

public interface BoardMapper {
	
//	@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	
	//id속성 값은 메서드와 일치해야 한다. 
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
}
