package com.datajpa.ahmetergun.repository;

import com.datajpa.ahmetergun.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
