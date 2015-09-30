package com.cms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cms.constants.SQLConstants;
import com.cms.exceptions.CMSException;

final public class DBUtil {

	private static final Logger LOG = Logger.getLogger(DBUtil.class);

	private DBUtil() {
	}

	public static Connection getConnection() throws CMSException {
		LOG.info("In DBUtil.getConnection() method");
		Connection connection = null;
		try {
			Class.forName(SQLConstants.DRIVER_NAME);
			connection = DriverManager.getConnection(SQLConstants.DRIVER_URL,
					SQLConstants.DB_USER_NAME, SQLConstants.DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new CMSException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CMSException(e);
		}
		LOG.info("Exiting DBUtil.getConnection() method");
		return connection;
	}
}