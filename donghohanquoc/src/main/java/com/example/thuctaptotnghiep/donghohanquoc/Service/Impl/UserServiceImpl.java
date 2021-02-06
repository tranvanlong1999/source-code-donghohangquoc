package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Converter.UserConverter;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.UserRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Override
    public UserOutput checkLogin(UserInput userInput) {
        UserEntity userEntity = userRepository.findUserEntitiesByUserNameAndPassWord(userInput);
        UserOutput userOutput= new UserOutput();
        if(!ObjectUtils.isEmpty(userEntity))
        {
            userOutput= userConverter.toUserUserEntity(userEntity);
            return userOutput;
        }
        return null;
    }
}
