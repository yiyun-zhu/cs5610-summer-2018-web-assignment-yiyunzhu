package com.example.webdev2018.sevices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import com.example.webdev2018.models.Course;
import com.example.webdev2018.models.Module;
import com.example.webdev2018.repositories.CourseRepository;
import com.example.webdev2018.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class ModuleService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	ModuleRepository moduleRepository;
	
	@GetMapping("/api/course/{courseId}/module") 
	public Iterable<Module> findAllModulesForCourse(
				@PathVariable("courseId")int courseId) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			return course.getModules();
		}
		return null;
	}
	
	@PostMapping("/api/course/{courseId}/module")
	public Module createModule(
					@PathVariable("courseId")int courseId,
					@RequestBody Module newModule) {
		Optional<Course> data = courseRepository.findById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			newModule.setCourse(course);
			return moduleRepository.save(newModule);
		}
		return null;
	}
	
	@DeleteMapping("/api/module/{mId}")
	public void deleteModule(
					@PathVariable("mId")int moduleId) {
		moduleRepository.deleteById(moduleId);
	}

}
