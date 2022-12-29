package com.cali.springbootrestapi.controller;

import com.cali.springbootrestapi.dto.UserDto;
import com.cali.springbootrestapi.entity.User;
import com.cali.springbootrestapi.service.UserService;
import com.cali.springbootrestapi.util.CustomPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user){
        UserDto resultUser = userService.createUser(user);
        return ResponseEntity.ok(resultUser);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> resultUsers = userService.getAllUsers();
        return  ResponseEntity.ok(resultUsers);
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        UserDto resultUser = userService.getUser(id);
        return ResponseEntity.ok(resultUser);

    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,@RequestBody UserDto user){
        UserDto resultUser = userService.updateUser(id, user);
        return ResponseEntity.ok(resultUser);

    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<User>> pagination(@RequestParam int currentPage, @RequestParam int pageSize){
        return ResponseEntity.ok(userService.pagination(currentPage,pageSize));
    }

    @GetMapping("/pagination/v1")
    public ResponseEntity<Page<User>> pagination(Pageable pageable){
        return ResponseEntity.ok(userService.pagination(pageable));
    }

    @GetMapping("/pagination/v2")
    public ResponseEntity<Slice<User>> slice(Pageable pageable){
        return ResponseEntity.ok(userService.slice(pageable));
    }

    @GetMapping("/pagination/v3")
    public ResponseEntity<CustomPage<UserDto>> customPagination(Pageable pageable){
        return ResponseEntity.ok(userService.customPagination(pageable));
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        Boolean resultUser =userService.deleteUser(id);
        return ResponseEntity.ok(resultUser);
    }

}
