package com.example.webdev2018.sevices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import com.example.webdev2018.models.Lesson;
import com.example.webdev2018.models.Module;
import com.example.webdev2018.repositories.LessonRepository;
import com.example.webdev2018.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins="*", maxAge=3600)
public class LessonService {
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/course/{courseId}/module/{mId}/lesson")
	public Lesson createLesson(
					@PathVariable("courseId")int courseId,
					@PathVariable("mId")int moduleId,
					@RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			newLesson.setModule(module);
			return lessonRepository.save(newLesson);
		}
		return null;
	}
	
	@GetMapping("/api/module/{mId}/lesson")
	public Iterable<Lesson> findAllLessonsForModule(
				@PathVariable("mId")int moduleId) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		return null;
	}
	
	@DeleteMapping("/api/lesson/{lId}")
	public void deleteCourse(
			@PathVariable("lId")int lessonId) {
		lessonRepository.deleteById(lessonId);
	}
}
