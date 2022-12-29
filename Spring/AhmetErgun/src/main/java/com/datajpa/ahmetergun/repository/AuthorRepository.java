package com.datajpa.ahmetergun.repository;

import com.datajpa.ahmetergun.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {
}
