package com.example.thuctaptotnghiep.donghohanquoc.Converter;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.LoginInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserOutput toUserEntity(UserEntity userEntity)
    {
        UserOutput userOutput= new UserOutput();
        userOutput.setId(userEntity.getID());
        userOutput.setUsername(userEntity.getUserName());
        userOutput.setFullname(userEntity.getFullName());
        userOutput.setPhone(userEntity.getPhone());
        userOutput.setSex(userEntity.getSex());
        userOutput.setAddress(userEntity.getAddress());
        userOutput.setRole(userEntity.getRole());
        userOutput.setStatus(userEntity.getStatus());
        return userOutput;
    }
    public UserEntity  toUserInput(UserInput userInput)
    {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userInput.getUsername());
        userEntity.setPassWord(userInput.getPassword());
        userEntity.setFullName(userInput.getFullname());
        userEntity.setPhone(userInput.getPhone());
        userEntity.setAddress(userInput.getAddress());
        userEntity.setStatus(userInput.getStatus());
        userEntity.setRole(userInput.getRole());
        userEntity.setEmail(userInput.getEmail());
        userEntity.setSex(userInput.getSex());
        userEntity.setCreateBy(userInput.getCreatedby());
        userEntity.setJoinedAt(userInput.getJoinedat());
        return userEntity;
    }
    public UserEntity toLoginInput(LoginInput loginInput)
    {
        UserEntity userEntity= new UserEntity();
        userEntity.setUserName(loginInput.getUsername());
        userEntity.setPassWord(loginInput.getPassword());
        return userEntity;
    }
}
