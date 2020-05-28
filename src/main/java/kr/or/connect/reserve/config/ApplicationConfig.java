package kr.or.connect.reserve.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"kr.or.connect.reserve.daoImpl", "kr.or.connect.reserve.serviceImpl"})
@Import({DBConfig.class}) 
public class ApplicationConfig {

}
