package com.datajpa.ahmetergun.repository;

import com.datajpa.ahmetergun.model.Zipcode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZipcodeRepository extends CrudRepository<Zipcode,Long> {
}
