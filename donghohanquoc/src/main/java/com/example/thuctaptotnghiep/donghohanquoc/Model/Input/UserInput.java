package com.example.thuctaptotnghiep.donghohanquoc.Model.Input;

import lombok.Data;

import java.util.Date;

@Data
public class UserInput {
    private String username;
    private String password;
    private String fullname;
    private String phone;
    private String address;
    private int status;
    private int role;
    private String email;
    private int sex;
    private Date joinedat;
    private String createby;

}
