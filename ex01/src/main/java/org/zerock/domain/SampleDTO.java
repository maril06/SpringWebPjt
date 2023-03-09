package org.zerock.domain;

import lombok.Data;

/* @Data 어노테이션 => getter/setter/equals()/toString()등의 메서드를 자동으로 생성해줌.
 * */
@Data
public class SampleDTO {
	private String name;
	private int age;
}
