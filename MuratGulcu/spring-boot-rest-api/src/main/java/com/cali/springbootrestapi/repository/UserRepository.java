package com.cali.springbootrestapi.repository;

import com.cali.springbootrestapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
   /* User findByFirstName(String firstName);
    User findByFirstNameAndLastName(String firstName, String lastname);*/

//    @Query("")
//    User getUser(String user);
}
