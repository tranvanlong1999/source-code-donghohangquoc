package com.example.thuctaptotnghiep.donghohanquoc.Repository;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntitiesByUserNameAndPassWord(UserInput userInput);
}
