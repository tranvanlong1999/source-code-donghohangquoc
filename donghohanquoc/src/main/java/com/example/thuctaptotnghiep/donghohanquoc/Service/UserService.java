package com.example.thuctaptotnghiep.donghohanquoc.Service;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Output.UserOutput;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserOutput checkLogin(UserInput userInput);
}
