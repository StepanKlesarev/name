package com.example.name.Repository;


import com.example.name.Models.Zoo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZooRepository extends CrudRepository<Zoo, Long> {

    public List<Zoo> findByTitle(String title);
    public List<Zoo> findByTitleContains(String title);

}