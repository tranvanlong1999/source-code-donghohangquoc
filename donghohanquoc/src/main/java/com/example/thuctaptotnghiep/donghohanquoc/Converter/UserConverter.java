package com.example.thuctaptotnghiep.donghohanquoc.Converter;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.LoginInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Component
public class UserConverter {
    public UserOutput toUserEntity(UserEntity userEntity)
    {
        UserOutput userOutput= new UserOutput();
        if(!ObjectUtils.isEmpty(userEntity))
        {
            userOutput.setId(userEntity.getID());
            userOutput.setUsername(userEntity.getUserName());
            userOutput.setFullname(userEntity.getFullName());
            userOutput.setPhone(userEntity.getPhone());
            userOutput.setSex(userEntity.getSex());
            userOutput.setAddress(userEntity.getAddress());
            userOutput.setRole(userEntity.getRole());
            userOutput.setStatus(userEntity.getStatus());
            userOutput.setCreatedby(userEntity.getCreateBy());
            userOutput.setForgetcode(userEntity.getForgetCode());
            userOutput.setForgetcodedate(userEntity.getForgetCodeDate());
            userOutput.setJoinedat(userEntity.getJoinedAt());
            userOutput.setUpdatedat(userEntity.getUpdatedAt());
            userOutput.setUpdatedby(userEntity.getUdatedBy());
            userOutput.setEmail(userEntity.getEmail());
            userOutput.setPassword(userEntity.getPassWord());
        }
        return userOutput;
    }
    public UserEntity  toUserInput(UserInput userInput)
    {
        UserEntity userEntity = new UserEntity();
        if(!ObjectUtils.isEmpty(userInput))
        {
            userEntity.setUserName(userInput.getUsername());
            //Trong đó BCrypt.gensalt xác định số vòng, số vòng dao động từ 4-30, số vòng càng lớn thì thời gian thực hiện băm càng lâu.
           /* userEntity.setPassWord(BCrypt.hashpw(userInput.getPassword(),BCrypt.gensalt(12)));*/
            userEntity.setPassWord(userInput.getPassword());
            userEntity.setFullName(userInput.getFullname());
            userEntity.setPhone(userInput.getPhone());
            userEntity.setAddress(userInput.getAddress());
            userEntity.setStatus(1);
            userEntity.setRole(userInput.getRole());
            userEntity.setEmail(userInput.getEmail());
            userEntity.setSex(userInput.getSex());
            userEntity.setCreateBy(userInput.getCreatedby());
            userEntity.setJoinedAt(new Date());
            userEntity.setCreateBy(userInput.getFullname());
        }
        return userEntity;
    }

    public UserEntity  toLoginInput(LoginInput userInput)
    {
        UserEntity userEntity = new UserEntity();
        if(!ObjectUtils.isEmpty(userInput))
        {
            userEntity.setUserName(userInput.getEmail());
            /*userEntity.setPassWord(BCrypt.hashpw(userInput.getPassword(),BCrypt.gensalt(12)));*/
            userEntity.setPassWord(userInput.getPassword());
        }
        return userEntity;
    }
    /*public UserEntity toLoginInput(LoginInput loginInput)
    {
        UserEntity userEntity= new UserEntity();
        if(!ObjectUtils.isEmpty(loginInput))
        {
            userEntity.setUserName(loginInput.getUsername());
            userEntity.setPassWord(loginInput.getPassword());
        }

        return userEntity;
    }*/
    public UserEntity toUserUpdateInput(UserUpdateInput userUpdateInput)
    {
        UserEntity userEntity = new UserEntity();
        if(!ObjectUtils.isEmpty(userUpdateInput))
        {
            userEntity.setID(userUpdateInput.getId());
            userEntity.setUserName(userUpdateInput.getUsername());
            if(!userUpdateInput.getPassword().isEmpty())
                userEntity.setPassWord(BCrypt.hashpw(userUpdateInput.getNewPassword(),BCrypt.gensalt(12)));
            userEntity.setFullName(userUpdateInput.getFullname());
            userEntity.setPhone(userUpdateInput.getPhone());
            userEntity.setAddress(userUpdateInput.getAddress());
            userEntity.setForgetCode(userUpdateInput.getForgetcode());
            userEntity.setStatus(userUpdateInput.getStatus());
            userEntity.setRole(userUpdateInput.getRole());
            userEntity.setEmail(userUpdateInput.getEmail());
            userEntity.setForgetCodeDate(userUpdateInput.getForgetcodedate());
            userEntity.setSex(userUpdateInput.getSex());
            userEntity.setJoinedAt(userUpdateInput.getJoinedat());
            userEntity.setCreateBy(userUpdateInput.getCreatedby());
            userEntity.setUpdatedAt(userUpdateInput.getUpdatedat());
            userEntity.setUdatedBy(userUpdateInput.getUpdatedby());
        }
        return userEntity;
    }
}
