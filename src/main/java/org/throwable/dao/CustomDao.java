package org.throwable.dao;

import org.springframework.stereotype.Repository;

/**
 * @author throwable
 * @version v1.0
 * @description
 * @since 2017/7/3 1:01
 */
@Repository
public class CustomDao {

	//模拟查询
	public String select() {
		return "select success!";
	}
}
