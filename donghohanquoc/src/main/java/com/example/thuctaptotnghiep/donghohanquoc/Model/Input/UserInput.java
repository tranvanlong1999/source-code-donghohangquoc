package com.example.thuctaptotnghiep.donghohanquoc.Model.Input;

import lombok.Data;

import java.util.Date;

@Data
public class UserInput {
    // khi truyền dữ liệu từ  client phải giống với biến ở đây
    private String password;
    private String fullname;
    private String phone;
    private String address;
    private int status;
    private int role;
    private String email;
    private Date joinedat;
    private String createdby;
    private String rePassword;
}
