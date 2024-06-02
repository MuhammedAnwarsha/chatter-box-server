package com.chatter.service;

import com.chatter.config.JwtProvider;
import com.chatter.modal.User;
import com.chatter.repository.UserRepository;
import com.chatter.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserById(Integer id) throws Exception {

        Optional<User> opt = userRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new Exception("user not found with id" +id);
    }

    @Override
    public User findUserProfile(String jwt) throws Exception {

        String email = JwtProvider.getEmailFromToken(jwt);
        if (email == null){
            throw new Exception("received invalid token");
        }
        User  user = userRepository.findByEmail(email);
        if (user==null){
            throw new Exception("user not found with email "+email);
        }
        return user;
    }

    @Override
    public User updateUser(Integer userId, UpdateUserRequest req) throws Exception {

        User user = findUserById(userId);
        if (req.getFull_name()!=null){
            user.setFull_name(req.getFull_name());
        }
        if (req.getProfile_picture()!=null){
            user.setProfile_picture(req.getProfile_picture());
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> searchUser(String query) {

        return userRepository.searchUser(query);
    }
}
