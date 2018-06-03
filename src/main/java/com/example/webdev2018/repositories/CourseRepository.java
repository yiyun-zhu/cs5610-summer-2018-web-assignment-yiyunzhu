package com.example.webdev2018.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev2018.models.Course;

public interface CourseRepository 
	extends CrudRepository<Course, Integer>{

}
