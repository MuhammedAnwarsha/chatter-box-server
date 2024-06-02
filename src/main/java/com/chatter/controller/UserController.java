package com.chatter.controller;

import com.chatter.modal.User;
import com.chatter.request.UpdateUserRequest;
import com.chatter.response.ApiResponse;
import com.chatter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findUserProfile(token);
        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{query}")
    public ResponseEntity<List<User>> searchUserHandler(@PathVariable("query") String query) throws Exception {

        List<User> users = userService.searchUser(query);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserHandler(@RequestHeader("Authorization") String token,
                                                         @RequestBody UpdateUserRequest req) throws Exception {

        User user = userService.findUserProfile(token);
        userService.updateUser(user.getId(), req);

        ApiResponse res = new ApiResponse("user updated successfully",true);
        return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
    }
}
