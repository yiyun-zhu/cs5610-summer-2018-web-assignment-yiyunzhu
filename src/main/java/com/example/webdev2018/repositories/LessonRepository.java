package com.example.webdev2018.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev2018.models.Lesson;

public interface LessonRepository 
		extends CrudRepository<Lesson, Integer>{

}
