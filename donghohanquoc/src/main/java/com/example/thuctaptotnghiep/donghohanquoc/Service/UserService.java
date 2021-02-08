package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.LoginInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResponseData<UserOutput> checkLogin(LoginInput loginInput);
    ResponseData<Boolean> createUserByAdmin(UserInput userInput);
    ResponseData<List<UserOutput>> getListUser();
    ResponseData<Boolean> deleteUserById(Integer id);
    ResponseData<Boolean> updateUserByAdmin(UserUpdateInput userUpdateInput);
}
