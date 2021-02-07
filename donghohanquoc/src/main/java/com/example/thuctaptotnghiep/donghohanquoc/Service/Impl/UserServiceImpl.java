package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Constants.ResCode;
import com.example.thuctaptotnghiep.donghohanquoc.Converter.UserConverter;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
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

    @Override
    public ResponseData<Boolean> deleteUserById(Integer id) {
        ResponseData<Boolean> responseData= new ResponseData<>();
        try
        {
            // get user by ID
            UserEntity userEntity= userRepository.findByID(id);
            if(ObjectUtils.isEmpty(userEntity))
            {
                throw new Exception();
            }
            userRepository.delete(userEntity);
            responseData.setCode(ResCode.SUCCESS.getCode());
            responseData.setMessage(ResCode.SUCCESS.getMessage());
            responseData.setData(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }

}
