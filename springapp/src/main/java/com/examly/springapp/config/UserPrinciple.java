package com.examly.springapp.config;
 
import java.util.Collection;
import java.util.List;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
import com.examly.springapp.model.User;
 
public class UserPrinciple{
    // private User user;
    // public UserPrinciple(User user) {
    //     this.user = user;
    // }
    // public static UserDetails build(User user) {
    //     return new UserPrinciple(user);
    // }
    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //    // return List.of(()->"ROLE_"+user.getRole());
    //    return List.of(()->"ROLE_"+user.getUserRole());
    // }
    // @Override
    // public String getPassword() {
    //     return user.getPassword();
    // }
    // @Override
    // public String getUsername() {
    //     return user.getUsername();
    // }
    // @Override
    // public boolean isAccountNonExpired() {
    //     // TODO Auto-generated method stub
    //     return false;
    // }
    // @Override
    // public boolean isAccountNonLocked() {
    //     // TODO Auto-generated method stub
    //     return false;
    // }
    // @Override
    // public boolean isCredentialsNonExpired() {
    //     // TODO Auto-generated method stub
    //     return false;
    // }
    // @Override
    // public boolean isEnabled() {
    //     // TODO Auto-generated method stub
    //     return false;
    // }
}