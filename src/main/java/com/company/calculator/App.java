package com.company.calculator;

import com.company.calculator.config.WebConfig;
import com.company.calculator.service.MainService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan("com.company.calculator")
public class App
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        new WebConfig(ctx.getBean(MainService.class));
        ctx.registerShutdownHook();
    }
}
