package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
		/*MyBatis 어노테이션을 이용해서 SQL을 메서드에 추가*/
		@Select("SELECT sysdate FROM dual")
		public String getTime();
		
		public String getTime2();

}
