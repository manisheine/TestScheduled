package com.vinculum.dynamic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.vinculum.dynamic.constants.Constants;
import com.vinculum.dynamic.dao.objects.TestDaoObject;
import com.vinculum.dynamic.operation.DataOperation;

public class TestService {

	//Inject Session Object
	private SqlSessionTemplate session;
	//Inject Manager Object
	private DataSourceTransactionManager manager;
	//Inject Data Source Object [Not using this object currently]
	public Map<String,DriverManagerDataSource> datasources;
	//Create the redis template
	private RedisTemplate<String,TestRedisObject> redisTemplate;

	//This is the test execute method
	public void execute() {
		//Create the Object Of Test Operation
		final DataOperation operation = new DataOperation(manager, session);
		//Create the List
		final List<TestDaoObject> objects = new ArrayList<>();
		//Create A Loop to Generate 10 Random Numbers
		for(int index=1;index<=10;index++) {
			//Create Random Object
			Random randomNum = new Random();
			//Get the Next int between 1 to 100
			int data = randomNum. nextInt(100);
			//Create the TestDaoObject
			final TestDaoObject object = new TestDaoObject();
			//Set the Random
			object.setRandom(data);
			//Add to List
			objects.add(object);
		}
		//Call the operation
		operation.test(objects);
	}

	/** This Method is Responsible to Get the Value Stored for A Key
	 * @param logString 
	 * @return
	 */
	public String expires(final String key) {
		//Declare
		String token = Constants.BLANK;
		//Create Bound
		final BoundValueOperations<String, TestRedisObject>  ops =redisTemplate.boundValueOps(key);
		//Get the object
		final TestRedisObject object = ops.get();
		//Get the Token
		token = object.getToken();
		//Get the TTL
		final Long ttl = object.getTtl();
		//Print the Token
		System.out.println(token);
		//Print the TTL
		System.out.println(ttl);
		//Return
		return token;
	}

	/** This Method Is Responsible to Set the Value to A Key
	 * @param token
	 * @param logString 
	 * @return
	 */
	public void set(final String key, final String token) {
		//Create Bound
		final BoundValueOperations<String, TestRedisObject>  ops =redisTemplate.boundValueOps(key);
		//Create the Object
		final TestRedisObject object = new TestRedisObject();
		//Set the token with TTL in minutes
		object.setToken(token, 5);
		//Insert
		ops.set(object);

	}

	public void setSession(SqlSessionTemplate session) {
		this.session = session;
	}
	public void setManager(DataSourceTransactionManager manager) {
		this.manager = manager;
	}
	public void setDatasources(Map<String, DriverManagerDataSource> datasources) {
		this.datasources = datasources;
	}

	public RedisTemplate<String, TestRedisObject> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, TestRedisObject> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

}
