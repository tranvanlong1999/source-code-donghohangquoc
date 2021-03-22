package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.LoginInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserUpdateInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.ResponseData;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface UserService {
    ResponseData<Boolean> createUserByAdmin(UserInput userInput);
    /*UserOutput checkLogin(LoginInput loginInput);*//*

    ResponseData<List<UserOutput>> getListUser();
    ResponseData<Boolean> deleteUserById(Integer id);
    ResponseData<Boolean> updateUserByAdmin(UserUpdateInput userUpdateInput);*/
    String login(Model model, HttpSession session, HttpServletResponse response, LoginInput userForm);
    String register(Model model, UserInput userInput);
    ResponseData<List<UserOutput>> getListUser();
    String pageLogout(Model model, HttpSession session, HttpServletResponse response);

}
