package com.example.webdev2018.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Widget {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String widgetType;
	private String text;
	private int size;
	private String href;
	private String src;
	private String listType;
	private String listItems;
	@ManyToOne
	@JsonIgnore
	private Lesson lesson;
	
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getListItems() {
		return listItems;
	}
	public void setListItems(String listItems) {
		this.listItems = listItems;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Lesson getLesson() {
		return lesson;
	}
	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
