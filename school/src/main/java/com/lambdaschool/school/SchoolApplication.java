package com.lambdaschool.school;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// Allows me to do my own routing...don't do this unless I'm using John's trick
@EnableWebMvc
@EnableJpaAuditing
@SpringBootApplication
public class SchoolApplication
{

    public static void main(String[] args)
    {

        //will return the bean I spelled out, or nothing. Everything works or my exceptions won't work.
        ApplicationContext ctx = SpringApplication.run(SchoolApplication.class, args);
        DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}
