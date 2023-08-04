package com.vinculum.dynamic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.executor.BatchResult;

import com.vinculum.dynamic.dao.objects.TestDaoObject;

public interface TestDao {
	
	public void testInsert(final TestDaoObject object);
	
	@Flush
	public List<BatchResult> flush();

}
