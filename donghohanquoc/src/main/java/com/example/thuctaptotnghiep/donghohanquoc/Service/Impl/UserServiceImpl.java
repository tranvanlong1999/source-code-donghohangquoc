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

import java.util.LinkedList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserOutput checkLogin(UserInput userInput) {
         UserEntity userEntity = userRepository.findByUserNameAndPassWord(userInput.getUsername(),userInput.getPassword());
         return userConverter.toUserUserEntity(userEntity);
    }

    @Override
    public UserOutput SaveUser(UserInput userInput) {
        UserEntity userEntity= userConverter.toUserInput(userInput);
        userEntity=userRepository.save(userEntity);
        return userConverter.toUserUserEntity(userEntity);
    }

    @Override
    public List<UserOutput> getListUser() {
        List<UserEntity> userEntityList= userRepository.findAll();
        List<UserOutput> userOutputs= new LinkedList<>();
        for (UserEntity userEntity:userEntityList) {
            userOutputs.add(new UserConverter().toUserUserEntity(userEntity));
        }
        return userOutputs;
    }
}
