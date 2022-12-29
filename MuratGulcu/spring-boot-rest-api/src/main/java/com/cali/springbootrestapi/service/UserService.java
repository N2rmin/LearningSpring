package com.cali.springbootrestapi.service;

import com.cali.springbootrestapi.dto.UserDto;
import com.cali.springbootrestapi.entity.User;
import com.cali.springbootrestapi.util.CustomPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto user);

    List<UserDto> getAllUsers();

    UserDto getUser(Long id);

    UserDto updateUser(Long id, UserDto user);

    Boolean deleteUser(Long id);

    Page<User> pagination(int currentPage, int pageSize);
    Page<User> pagination(Pageable pageable);

    Slice<User> slice(Pageable pageable);

    CustomPage<UserDto> customPagination (Pageable pageable);
}
