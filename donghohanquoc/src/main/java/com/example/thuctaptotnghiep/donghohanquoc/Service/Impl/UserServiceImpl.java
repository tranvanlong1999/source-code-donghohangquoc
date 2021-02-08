package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Constants.Constants;
import com.example.thuctaptotnghiep.donghohanquoc.Constants.ResCode;
import com.example.thuctaptotnghiep.donghohanquoc.Converter.UserConverter;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Base.CustomException;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.LoginInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.UserRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.UserService;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public ResponseData<UserOutput> checkLogin(LoginInput loginInput) {
         ResponseData<UserOutput> responseData = new ResponseData<>();
         try {
             UserOutput userOutput = new UserOutput();
             UserEntity userEntity = userRepository.findByUserNameAndPassWord(loginInput.getUsername(),loginInput.getPassword());
             userOutput= userConverter.toUserEntity(userEntity);
             responseData.setCode(ResCode.SUCCESS.getCode());
             responseData.setMessage(ResCode.SUCCESS.getMessage());
             responseData.setData(userOutput);
         } catch (Exception e) {
             responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
             responseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
         }
        return responseData;
    }

    @Override
    public ResponseData <Boolean> createUserByAdmin(UserInput userInput) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        try
        {
            if(!ObjectUtils.isEmpty(userInput))
            {
                if(!ObjectUtils.isEmpty(userRepository.findByEmail(userInput.getEmail())))
                {
                    throw new CustomException(Constants.ERROR_CODE,"Email đã tồn tại trong hệ thống");
                }
                // convert userInput-> userEntity
                UserEntity userEntity= userConverter.toUserInput(userInput);
                // Insert vào bảng user trong cơ sở dữ liệu
                userEntity=userRepository.save(userEntity);
                // set Respondate
                responseData.setCode(ResCode.SUCCESS.getCode());
                responseData.setMessage(ResCode.SUCCESS.getMessage());
                responseData.setData(true);
            }

            //return userConverter.toUserUserEntity(userEntity);
        } catch (CustomException e) {
                responseData.setCode(e.getErrorCode());
                responseData.setMessage(e.getErrorDesc());
                responseData.setData(false);
        } catch (Exception e)
        {
                responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
                responseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
                responseData.setData(false);
        }
        return responseData;
    }

    @Override
    public ResponseData< List<UserOutput> > getListUser() {
        ResponseData<List<UserOutput>> responseData= new ResponseData<>();
        try {
            List<UserEntity> userEntityList= userRepository.findAll();
            List<UserOutput> userOutputs= new LinkedList<>();
            for (UserEntity userEntity:userEntityList) {
                userOutputs.add(new UserConverter().toUserEntity(userEntity));
            }
            responseData.setCode(ResCode.SUCCESS.getCode());
            responseData.setMessage(ResCode.SUCCESS.getMessage());
            responseData.setData(userOutputs);

        } catch (Exception e) {
            responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
            responseData.setCode(ResCode.UNKNOWN_ERROR.getMessage());
        }
        return  responseData;
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
            responseData.setCode(ResCode.UNKNOWN_ERROR.getCode());
            responseData.setMessage(ResCode.UNKNOWN_ERROR.getMessage());
            responseData.setData(false);
        }
        return responseData;
    }

    @Override
    public ResponseData<Boolean> updateUserByAdmin(UserUpdateInput userUpdateInput) {
        ResponseData<Boolean> responseData= new ResponseData<>();
        try
        {
            // get user từ user ID
            UserEntity userEntity = userRepository.findByID(userUpdateInput.getId());
            boolean checkPassWordIsEmpty= false;
            if(userUpdateInput.getPassword().isEmpty())
                checkPassWordIsEmpty=true;
            // case email không tồn tại trong hệ thống
            if(ObjectUtils.isEmpty(userEntity))
            {
                throw new CustomException(Constants.ERROR_CODE,"user không tồn tại trong hệ thống");
            }
            // case email đã có trong hệ thống
            if(!ObjectUtils.isEmpty(userEntity))
            {
                throw new CustomException(Constants.ERROR_CODE,"Email tồn tại trong hệ thống");
            }
            //if(checkPassWordIsEmpty==false && )

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseData;
    }

}
