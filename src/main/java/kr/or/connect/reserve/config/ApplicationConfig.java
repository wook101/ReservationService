package kr.or.connect.reserve.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"kr.or.connect.reserve.dao", "kr.or.connect.reserve.serviceimpl"})
@Import({DBConfig.class}) 
public class ApplicationConfig {

}
