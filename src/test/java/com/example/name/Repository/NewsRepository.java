package com.example.name.Repository;

import com.example.name.Models.News;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {

    public List<News> findByTitle(String title);
    public List<News> findByTitleContains(String title);

}