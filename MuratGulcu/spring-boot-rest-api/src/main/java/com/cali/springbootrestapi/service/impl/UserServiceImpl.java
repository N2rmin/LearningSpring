package com.cali.springbootrestapi.service.impl;

import com.cali.springbootrestapi.config.ModelMapperConfig;
import com.cali.springbootrestapi.dto.UserDto;
import com.cali.springbootrestapi.entity.User;
import com.cali.springbootrestapi.exception.UserNotFound;
import com.cali.springbootrestapi.repository.UserRepository;
import com.cali.springbootrestapi.service.UserService;
import com.cali.springbootrestapi.util.CustomPage;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto useDto) {
        User user =modelMapper.map(useDto,User.class);
        user.setCreatedDate(new Date());
        user.setCreatedBy("Narmin");

        return modelMapper.map(userRepository.save(user),UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=userRepository.findAll();
        List<UserDto> dtos= users.stream().map(user ->modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return  dtos;
    }

    @Override
    public UserDto getUser(Long id) {
        Optional<User> user= userRepository.findById(id);

        if (user.isPresent()){
            return modelMapper.map( user.get(),UserDto.class);
        }
        throw new RuntimeException("Istifadeci tapilmadi");
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        Optional<User> resultUser= userRepository.findById(id);

        if (resultUser.isPresent()){
             resultUser.get().setFirstName(user.getFirstName());
             resultUser.get().setLastName(user.getLastName());
             resultUser.get().setUpdatedBy("Narmin");
             resultUser.get().setUpdateDate(new Date());

             return modelMapper.map( userRepository.save(resultUser.get()),UserDto.class);


        }
        return null;
    }

    @Override
    public Boolean deleteUser(Long id) {
        Optional<User> user= userRepository.findById(id);

        if (user.isPresent()){
            userRepository.deleteById(id);
            return true ;
        }
        return false;
    }

    @Override
    public Page<User> pagination(int currentPage, int pageSize) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);

        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> pagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Slice<User> slice(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public CustomPage<UserDto> customPagination(Pageable pageable) {
        Page<User> data=  userRepository.findAll(pageable);
      UserDto[] dtos =  modelMapper.map(data.getContent(),UserDto[].class);
        return new CustomPage<UserDto>(data, Arrays.asList(dtos));
    }


}
