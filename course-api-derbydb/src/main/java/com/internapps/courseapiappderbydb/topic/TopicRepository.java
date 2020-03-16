 package com.internapps.courseapiappderbydb.topic;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, String> {
	
	/* 
	 * DATA SERVICE CLASS
	 */
	
	/* getAllTopics()
	 * getAllTopics(String id)
	 * updateTopic(Topic t)
	 * deleteTopic(String id)
	 * 
	 * All of the above STANDARD CRUD methods are included by extending the CrudRepository
	 * interface. No need to redefine them here. Would only need to define any custom requests
	 * eg: some custom method like "Find all topics containing x" - ie: non standard CRUD operation
	*/
	
	
	
	/*
	 * IN THIS INSTANCE, TopicRepository is simply an alias for the standard CRUDRepository - as there is
	 * no special implementations. See TopicService for the various method calls to perform CRUD operations.
	 */

}
