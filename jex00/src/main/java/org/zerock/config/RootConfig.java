package org.zerock.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* root-context.xml의 역할을 함. 빈을 등록함
 * 
 * */
@Configuration
@ComponentScan(basePackages= {"org.zerock.sample"})
public class RootConfig {

}
