package com.examly.springapp.utility;

import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;

public class UserMapper {
     /**
     * Converts a User object into a LoginDTO object.
     * 
     * The User object containing user details.
     * return A LoginDTO object with selected user information.
     */
    private UserMapper(){}
    public static LoginDTO mappedToLoginDTO(User user){
        String token="token"; // coming from jwtUtils
        LoginDTO loginDTO= new LoginDTO();
        loginDTO.setToken(token);
        loginDTO.setUserId(user.getUserId());
        loginDTO.setUserRole(user.getUserRole());
        loginDTO.setUsername(user.getUsername());
        return loginDTO;
    }
}
