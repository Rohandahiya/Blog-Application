package com.example.blogApplication.configurations;

//import com.example.blogApplication.serviceImpl.UserDummyServiceImpl;
import com.example.blogApplication.serviceImpl.UserServiceImpl;
import com.example.blogApplication.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceImplSelectionConfiguration {

    @Bean
    @Qualifier("mainServiceImpl")
    public UserService getUserService(){
        return new UserServiceImpl();
    }

//    @Bean
//    @Qualifier("dummyServiceImpl")
//    public UserService getUserDummyService(){
//        return new UserDummyServiceImpl();
//    }
}
