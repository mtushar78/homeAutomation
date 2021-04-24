package com.example.homeautomation.repositories;
import com.example.homeautomation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    @Query(value = "select count(*) from user where sso_id = :sso_id",
            nativeQuery = true)
    int findBySso_id(String sso_id);
    @Query(value= "select id from user where email = :email", nativeQuery = true)
    Integer getUserId(String email);
}
