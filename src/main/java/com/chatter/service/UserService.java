package com.chatter.service;

import com.chatter.modal.User;
import com.chatter.request.UpdateUserRequest;

import java.util.List;

public interface UserService {

    public User findUserById(Integer id) throws Exception;

    public User findUserProfile(String jwt) throws Exception;

    public User updateUser(Integer userId, UpdateUserRequest req)throws Exception;

    public List<User> searchUser(String query);
}
