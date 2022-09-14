package com.example.name.Repository;

import com.example.name.Models.Igryshki;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IgryshkiRepository extends CrudRepository<Igryshki, Long> {

    public List<Igryshki> findByTitle(String title);
    public List<Igryshki> findByTitleContains(String title);

}