package com.techietact.tutorial.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.okhttp.OkHttpClient;

@Configuration
@EnableFeignClients(basePackages = "com.techietact.tutorial.service")
public class FeignClientConfig {
	 
	@Bean
	OkHttpClient client() {
	   return new OkHttpClient();
	} 
}
