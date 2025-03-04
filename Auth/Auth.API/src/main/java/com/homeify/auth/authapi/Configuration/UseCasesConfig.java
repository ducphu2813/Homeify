package com.homeify.auth.authapi.Configuration;

import com.homeify.auth.Adapter.*;
import com.homeify.auth.DB.*;
import com.homeify.auth.JWT.JWTProviderImpl;
import com.homeify.auth.Mapper.RoleMapper;
import com.homeify.auth.Mapper.UsersMapper;
import com.homeify.auth.UseCases.AuthUsecase;
import com.homeify.auth.UseCases.RoleUsecase;
import com.homeify.auth.UseCases.UserUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    //đăng ký bean user adapter
    @Bean
    public UsersAdapter usersAdapter (UsersRepository usersRepository
                                        , UsersMapper usersMapper
                                        , RoleMapper roleMapper)
    {
        return new UsersAdapterImpl(usersRepository, usersMapper, roleMapper);
    }

    //đăng ký jwt provider
    @Bean
    public JWTProvider jwtProvider (){
        return new JWTProviderImpl();
    }

    //đăng ký bean role adapter
     @Bean
    public RoleAdapter roleAdapter (RoleRepository roleRepository
                                        , RoleMapper roleMapper)
    {
        return new RoleAdapterImpl(roleRepository, roleMapper);
    }

    //đăng ký bean user role adapter
     @Bean
    public UserRoleAdapter userRoleAdapter (UserRoleRepository userRoleRepository)
    {
        return new UserRoleAdapterImpl(userRoleRepository);
    }

    //đăng ký kafka producer adapter
    @Bean
    public KafkaProducerAdapter kafkaProducerAdapter (KafkaProducerAdapterImpl kafkaProducerAdapter){
        return kafkaProducerAdapter;
    }
    //---------------------
    ///--------------------
    ///--------------------

    //bean user use case
    @Bean
    public UserUsecase userUsecase (UsersAdapter usersAdapter
                                    , KafkaProducerAdapter kafkaProducerAdapter){
        return new UserUsecase(usersAdapter, kafkaProducerAdapter);
    }

    //bean auth use case
    @Bean
    public AuthUsecase authUsecase (UsersAdapter usersAdapter
                                    , JWTProvider jwtProvider){
        return new AuthUsecase(usersAdapter, jwtProvider);
    }

    //bean role use case
    @Bean
    public RoleUsecase roleUsecase (RoleAdapter roleAdapter
                                    , UserRoleAdapter userRoleAdapter){
        return new RoleUsecase(roleAdapter, userRoleAdapter);
    }
}
