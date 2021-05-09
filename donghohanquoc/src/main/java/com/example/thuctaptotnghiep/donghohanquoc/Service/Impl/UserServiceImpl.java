package com.example.thuctaptotnghiep.donghohanquoc.Service.Impl;

import com.example.thuctaptotnghiep.donghohanquoc.Common.Constant;
import com.example.thuctaptotnghiep.donghohanquoc.Common.MessageConstant;
import com.example.thuctaptotnghiep.donghohanquoc.Common.PageConstant;
import com.example.thuctaptotnghiep.donghohanquoc.Common.Validate;
import com.example.thuctaptotnghiep.donghohanquoc.Constants.Constants;
import com.example.thuctaptotnghiep.donghohanquoc.Converter.UserConverter;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.LoginInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import com.example.thuctaptotnghiep.donghohanquoc.Repository.UserRepository;
import com.example.thuctaptotnghiep.donghohanquoc.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public  List<UserOutput>  getListUser() {
        List<UserOutput> userOutputList= new LinkedList<>();
        try {
            Page<UserEntity> page= userRepository.findAll(
                    PageRequest.of(0,5)
            );
            for (UserEntity userEntity:page) {
                userOutputList.add(new UserConverter().toUserEntity(userEntity));
            }

        } catch (Exception e) {
        }
        System.out.println(userOutputList);
        return  userOutputList;
    }

    @Override
    public String pageLogout(Model model, HttpSession session, HttpServletResponse response) {
        session.removeAttribute("user");
        model.addAttribute("error", null);
        // remove a cookie email
        Cookie cookieEmail = new Cookie("email", null);
        cookieEmail.setMaxAge(0); // expires in 7 days

        // remove a cookie password
        Cookie cookiePassword = new Cookie("password", null);
        cookiePassword.setMaxAge(0); // expires in 7 days

        // remove a cookie remember
        Cookie cookieRemember = new Cookie("remember", null);
        cookieRemember.setMaxAge(0); // expires in 7 days

        // add cookie to response
        response.addCookie(cookieEmail);
        response.addCookie(cookiePassword);
        response.addCookie(cookieRemember);

        return "redirect:/dang-nhap";
    }

    @Override
    public String login(Model model, HttpSession session, HttpServletResponse response, LoginInput userForm) {
        String result = PageConstant.PAGE_LOGIN;
        String message = null;
        try {
            if (Validate.checkLogin(userForm)) {
                UserEntity user = userRepository.findByEmailAndPassWord(userForm.getEmail(),userForm.getPassword());

                if (ObjectUtils.isEmpty(user)) {
                    message = MessageConstant.LOGIN_ERROR;
                } else {
                    if (userForm.isRemember()) {
                        // create a cookie email
                        Cookie cookieEmail = new Cookie("email", user.getEmail());
                        cookieEmail.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

                        // create a cookie password
                        Cookie cookiePassword = new Cookie("password", user.getPassWord());
                        cookiePassword.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

                        // remove a cookie remember
                        Cookie cookieRemember = new Cookie("remember", "" + userForm.isRemember());
                        cookieRemember.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days

                        // add cookie to response
                        response.addCookie(cookieEmail);
                        response.addCookie(cookiePassword);
                        response.addCookie(cookieRemember);
                    } else {
                        // remove a cookie email
                        Cookie cookieEmail = new Cookie("email", null);
                        cookieEmail.setMaxAge(0); // expires in 7 days

                        // remove a cookie password
                        Cookie cookiePassword = new Cookie("password", null);
                        cookiePassword.setMaxAge(0); // expires in 7 days

                        // remove a cookie remember
                        Cookie cookieRemember = new Cookie("remember", null);
                        cookieRemember.setMaxAge(0); // expires in 7 days

                        // add cookie to response
                        response.addCookie(cookieEmail);
                        response.addCookie(cookiePassword);
                        response.addCookie(cookieRemember);
                    }
                    // save session
                    session.setAttribute("user", user);
                    session.setAttribute("email",user.getEmail());
                    result= "redirect:/home";
                }
            } else {
                message = MessageConstant.LOGIN_ERROR;
            }
            } catch (Exception e) {
            message = MessageConstant.LOGIN_ERROR;
        }

        model.addAttribute("error", message);
        return result;
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public String register(Model model, UserInput userInput) {
        String result = PageConstant.PAGE_REGISTER;
        String error = null;
        UserEntity user = null;
        try {
            // step 1: validate
            if (Validate.checkRegister(userInput)) {
                // step 2: check email exists
                if (ObjectUtils.isEmpty(userRepository.findByEmail(userInput.getEmail()))) {
                    // convert from register input to user entity
                    System.out.println("aa");
                    userInput.setRole(Constants.ROLE_MEMBER);
                    user = userConverter.toUserInput(userInput);
                    user.setStatus(Constant.STATUS_ENABLE);

//					user.setPassword(BCrypt.hashpw(userInput.getPassword(), BCrypt.gensalt(12)));

                    // step 3: save
                    userRepository.save(user);

                    // step 4: redirect page login
                    result = "redirect:/dang-nhap";
                } else {
                    error = MessageConstant.RIGISTER_ERROR;
                }
            } else {
                error = MessageConstant.RIGISTER_ERROR;
            }
        } catch (Exception e) {
            error = MessageConstant.RIGISTER_ERROR;
        }
        model.addAttribute("error", error);
        return result;
    }


}


