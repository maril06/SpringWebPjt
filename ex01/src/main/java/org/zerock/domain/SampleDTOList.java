package org.zerock.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SampleDTOList {
	
	private List<SampleDTO> list;
	
	public SampleDTOList() {
		System.out.println("DTO 생성중입니다.");
		list = new ArrayList<>();
	}
	
}
