package com.example.thuctaptotnghiep.donghohanquoc.Repository;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
        UserEntity findByEmailAndPassWord(String userName,String passWord);
        UserEntity findByUserName(String userName);
        UserEntity findByEmail(String email);
}
