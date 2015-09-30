package com.cms.constants;

public class SQLConstants {
	public static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	public static final String DRIVER_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String DB_USER_NAME = "hr";
	public static final String DB_PASSWORD = "Bijaya74";
	public static final String GET_COURSE_CODE_QUERY = "SELECT * FROM course_info WHERE course_code=?";
	public static final String COURSE_INFO_INSERT_QUERY = "INSERT INTO course_info VALUES(?,?,?,?)";
	public static final String COURSE_FEES_INSERT_QUERY = "INSERT INTO course_fees VALUES(?,?,?,?)";
	public static final String GET_COURSE_INFO_QUERY = "SELECT * FROM course_info WHERE course_code=?";
	public static final String GET_COURSE_FEES_QUERY = "SELECT * FROM course_fees WHERE course_code=?";
	public static final String GET_COURSE_DETAILS_QUERY = "SELECT course_info.course_code, course_info.course_name, course_info.course_description,course_info.number_of_participant,"
			+ "course_fees.course_type, course_fees.course_duration, course_fees.course_fee FROM "
			+ "course_info INNER JOIN course_fees ON course_info.course_code = course_fees.course_code "
			+ "WHERE course_info.course_code=?";

}
