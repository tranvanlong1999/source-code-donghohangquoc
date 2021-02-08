package com.example.thuctaptotnghiep.donghohanquoc.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user") // map voi database
@Data
public class UserEntity {
    private static final long serialVersionUID = -297553281792804396L;
    @Id // primary key va not null
    @GeneratedValue(strategy = GenerationType.IDENTITY)// tự tăng ID
    private int ID;
    @Column(name = "User_Name")
    private String userName;
    @Column(name = "Password")
    private String passWord;
    @Column(name = "Full_Name")
    private String fullName;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "Address")
    private String address;
    @Column(name = "Forget_Code")
    private String forgetCode;
    @Column(name = "Status")
    private int status;
    @Column(name = "Role")
    private int role;
    @Column(name = "Email")
    private String email;
    @Column(name = "Forget_Code_Date")
    private Date forgetCodeDate;
    @Column(name= "Sex")
    private int sex;
    @Column(name = "joined_At")
    private Date joinedAt;
    @Column(name="Created_By")
    private String createBy;
    @Column(name="Updated_By")
    private String udatedBy;
    @Column(name = "Updated_At")
    private Date updatedAt;
}
