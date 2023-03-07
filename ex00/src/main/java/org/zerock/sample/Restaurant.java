package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;


/*  @Component는 스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시한다.
 * @context:component-scan을 통해서 @Component가 존재하는 클래스들을 객체로 생성해 빈으로 관리한다.
 * 
 * 
 *  @Setter는 자동으로 setChef()를 컴파일 시 생성함.
 *  
 *  @Data는 Lombok에서 가장 자주 사용되는 어노테이션임, @ToString,@EqualsAndHashCode, 
 *  							@Getter/@Setter,@RequiredArgsConstructor를 모두 결합한 형태.
 *  
 * */

@Component
@Data
public class Restaurant {
	/*new chef()와 같이 객체를 생성한 적이 없는데도 객체가 만들어짐.*/
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
}
