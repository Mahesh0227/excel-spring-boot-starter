package com.bsh.excel.spring.boot.starter.config;

import com.bsh.excel.spring.boot.starter.core.ExcelProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExcelAutoConfiguration {

    @Bean
    public ExcelProcessor excelProcessor() {
        return new ExcelProcessor();
    }
}
