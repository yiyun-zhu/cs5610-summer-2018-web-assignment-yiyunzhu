package com.example.webdev2018.sevices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.webdev2018.models.Widget;
import com.example.webdev2018.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {

	@Autowired
	WidgetRepository repository;
	
	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>)repository.findAll();
	}
	
	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody 
			List<Widget> widgets) {
		repository.deleteAll();
		for(Widget widget : widgets) {
			repository.save(widget);
		}
	}
}
