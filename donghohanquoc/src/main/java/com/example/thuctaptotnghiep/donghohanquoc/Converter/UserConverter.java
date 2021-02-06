package com.example.thuctaptotnghiep.donghohanquoc.Converter;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserOutput toUserUserEntity(UserEntity userEntity)
    {
        UserOutput userOutput= new UserOutput();
        userOutput.setId(userEntity.getID());
        userOutput.setUsername(userEntity.getUserName());
        userOutput.setPhone(userEntity.getPhone());
        userOutput.setSex(userEntity.getSex());
        userOutput.setAddress(userEntity.getAddress());
        userOutput.setRole(userEntity.getRole());
        userOutput.setStatus(userEntity.getStatus());
        return userOutput;
    }
}
