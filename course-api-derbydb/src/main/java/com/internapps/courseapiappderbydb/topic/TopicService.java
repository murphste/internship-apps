package com.internapps.courseapiappderbydb.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
	
	/*
	 * SERVICE CLASS:
	 * It is this classes job to retrieve, process and perform any calculations
	 * on the data.
	 */
	
	@Autowired
	private TopicRepository topicRepository;

	
	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll()
		.forEach(topics::add);
		return topics;
	}
	
	public Optional<Topic> getTopic(String id) { 
		return topicRepository.findById(id);
	}
	
	
	public void addTopic(Topic topic) {
		 topicRepository.save(topic);
	}
	
	
	public void updateTopic(String id, Topic topic) {
		/*
		 * With this one - we can simply pass in the topic object.
		 * Since our object will have an id set on it, Spring knows enough
		 * to search for any existing object with that id and either update it if found,
		 * or do an insert (POST) if it is a new id. - Both handled via .save()
		 */
		topicRepository.save(topic);
	}

	public void deleteTopic(String id) {
		topicRepository.deleteById(id); 	
		
	}
		
	
}
