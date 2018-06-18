package com.example.webdev2018.sevices;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.webdev2018.models.Lesson;
import com.example.webdev2018.models.Widget;
import com.example.webdev2018.repositories.LessonRepository;
import com.example.webdev2018.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {

	@Autowired
	WidgetRepository repository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgets(@PathVariable("lessonId")int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lesson = data.get();
			return lesson.getWidgets();
		}
		return null;
	}
	
	@PostMapping("/api/lesson/{lessonId}/widget/save")
	public void saveAllWidgets(
			@PathVariable("lessonId")int lessonId,
			@RequestBody List<Widget> widgets) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lesson = data.get();
			List<Widget> listOfWidgets = lesson.getWidgets();
			for(Widget widget : listOfWidgets) {
				repository.delete(widget);
			}
			for(Widget widget : widgets) {
				widget.setLesson(lesson);
				repository.save(widget);
			}
		}
	}
}
