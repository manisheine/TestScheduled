package com.vinculum.dynamic.operation;

import java.util.List;

import org.apache.ibatis.executor.BatchResult;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.vinculum.dynamic.constants.Constants;
import com.vinculum.dynamic.dao.TestDao;
import com.vinculum.dynamic.dao.objects.TestDaoObject;
import com.vinculum.dynamic.datasource.CustomerContextHolder;
import com.vinculum.dynamic.datasource.Transactional;


public class DataOperation extends Transactional{

	/**
	 * Logger for this class.
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(DataOperation.class);
	/**
	 * The Session Object
	 */
	private SqlSessionTemplate session;
	/**
	 * The Default Constructor
	 */
	public DataOperation(final DataSourceTransactionManager manager, final SqlSessionTemplate session) {
		//Set the Transaction Manager
		setManager(manager);
		//Initialize
		this.session = session;
	}
	
	public void test(final List<TestDaoObject> objects) {
		//Handle Exception
		try {
			//Set the customer context
			CustomerContextHolder.setCustomerType(Constants.DEFAULT_DB);
			//Begin Transaction
			begin();
			
			//Get the mapper
			final TestDao dao = session.getMapper(TestDao.class);
			//Loop through the objects passed
			for(final TestDaoObject object: objects) {
				//Call the Insert
				dao.testInsert(object);
			}
			//Flush
			final List<BatchResult> result = dao.flush();
			//Just doing a pause  for debugging the result
			LOGGER.info(String.valueOf(result));
			//Commit Transaction
			commit();
		}catch(final Exception exception) {
			//Rollback the transaction
			rollback();
			//Log the exception
			LOGGER.error(exception.getMessage(), exception.fillInStackTrace());
		}finally {
			//Remove the context
			CustomerContextHolder.clearCustomerType();
		}
	}
}
