package com.example.thuctaptotnghiep.donghohanquoc.Repository;

import com.example.thuctaptotnghiep.donghohanquoc.Model.Entity.UserEntity;
import com.example.thuctaptotnghiep.donghohanquoc.Model.Input.UserInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // Khi truyền tham số ở bên này thì phải giống với bên entity.
    UserEntity findByUserNameAndPassWord(String username, String password);
    UserEntity findByID(Integer id);
    UserEntity findByEmail(String email);
    UserEntity findByEmailAndIDNot(String email,Integer id);

}
