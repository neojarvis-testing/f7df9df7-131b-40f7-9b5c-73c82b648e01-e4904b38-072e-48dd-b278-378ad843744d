package com.examly.springapp.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
 
import com.examly.springapp.model.User;
 
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    //Custom Query to find a User by their Email
    @Query("select user from User user where user.email=?1")
    User findByEmail(String email); 
}