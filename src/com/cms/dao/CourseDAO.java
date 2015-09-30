package com.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.cms.constants.SQLConstants;
import com.cms.exceptions.CMSException;
import com.cms.util.DBUtil;
import com.cms.vo.CourseVO;

public class CourseDAO {

	private static final Logger LOG = Logger.getLogger(CourseDAO.class);

	/* This function checks if courseCode exists in database */
	public boolean checkCourseCodeExistence(String courseCode)
			throws CMSException {

		System.out.println("In CourseDAO.checkCourseCodeExistence("
				+ courseCode + ")");

		LOG.info("In CourseDAO.checkCourseCodeExistence(" + courseCode + ")");
		boolean courseExistsFlag = false;
		Connection connection = DBUtil.getConnection();

		try {
			PreparedStatement pStmt = connection
					.prepareStatement(SQLConstants.GET_COURSE_CODE_QUERY);
			pStmt.setString(1, courseCode);
			System.out.println("Prepared statement made");
			ResultSet resultSet = pStmt.executeQuery();
			if (resultSet.next()) {
				courseExistsFlag = true;

				System.out.println("course exits: "
						+ resultSet.getString("number_of_participant"));

			} else {
				System.out.println("course does not exists: ");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error("SQLException in CourseDAO.checkCourseCodeExistence("
					+ courseCode + "): " + e);
			throw new CMSException(e.getMessage());
		}
		return courseExistsFlag;
	}

	/* This function adds course to the Database */
	public boolean addCourse(CourseVO courseVO) throws CMSException {

		LOG.info("In CourseDAO.addCourse(" + courseVO + ")");
		boolean courseAddedFlag = false;
		Connection connection = DBUtil.getConnection();
		if (connection != null) {

			try {
				PreparedStatement pStmtCourseInfo = connection
						.prepareStatement(SQLConstants.COURSE_INFO_INSERT_QUERY);
				pStmtCourseInfo.setString(1, courseVO.getCourseCode());
				pStmtCourseInfo.setString(2, courseVO.getCourseName());
				pStmtCourseInfo.setInt(3, courseVO.getNoOfParticipants());
				pStmtCourseInfo.setString(4, courseVO.getCourseDescription());

				PreparedStatement pStmtCourseFees = connection
						.prepareStatement(SQLConstants.COURSE_FEES_INSERT_QUERY);
				pStmtCourseFees.setString(1, courseVO.getCourseCode());
				pStmtCourseFees.setString(2, courseVO.getCourseType());
				pStmtCourseFees.setString(3, courseVO.getCourseDuration());
				pStmtCourseFees.setFloat(4, courseVO.getCourseFee());

				int updateCountCourseInfo = pStmtCourseInfo.executeUpdate();
				int updateCountCourseFees = pStmtCourseFees.executeUpdate();

				if (updateCountCourseInfo > 0 && updateCountCourseFees > 0) {
					courseAddedFlag = true;
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				LOG.error("SQLException in CourseDAO.addcCourse(" + courseVO
						+ "): ", e);
				throw new CMSException(e);
			}
		}

		return courseAddedFlag;
	}

	/* This function gets course details from the databases */
	public CourseVO getCourseDetails(String courseCode) throws CMSException {
		System.out.println("In CourseDAO.getCourseDetails(" + courseCode + ")");
		LOG.info("In CourseDAO.getCourseDetails(" + courseCode + ")");
		Connection connection = null;
		CourseVO courseVO = null;
		String courseName = "";
		String courseDescription = "";
		int noOfParticipants = 0;
		String courseType = "";
		String courseDuration = "";
		int courseFee = 0;

		try {
			connection = DBUtil.getConnection();

			// Use Join method to query the two tables together
			PreparedStatement getCourseDetailsPStmt = connection
					.prepareStatement(SQLConstants.GET_COURSE_DETAILS_QUERY);
			getCourseDetailsPStmt.setString(1, courseCode);
			ResultSet courseDetailsResultSet = getCourseDetailsPStmt
					.executeQuery();

			if (courseDetailsResultSet.next()) {

				courseName = courseDetailsResultSet.getString("course_name");
				courseDescription = courseDetailsResultSet
						.getString("course_description");
				noOfParticipants = Integer.parseInt(courseDetailsResultSet
						.getString("number_of_participant"));
				courseType = courseDetailsResultSet.getString("course_type");
				courseDuration = courseDetailsResultSet
						.getString("course_duration");
				courseFee = Integer.parseInt(courseDetailsResultSet
						.getString("course_fee"));

				courseVO = new CourseVO();
				courseVO.setCourseCode(courseCode);
				courseVO.setCourseName(courseName);
				courseVO.setCourseDescription(courseDescription);
				courseVO.setNoOfParticipants(noOfParticipants);
				courseVO.setCourseFee(courseFee);
				courseVO.setCourseType(courseType);
				courseVO.setCourseDuration(courseDuration);
			}

		} catch (Exception e) {
			// e.printStackTrace();
			LOG.error("Exception in CourseDAO.getCourseDetails(" + courseCode
					+ ")", e);
			throw new CMSException(e);
		}

		LOG.info("Exiting CourseDAO.getCourseDetails(" + courseCode + ")");
		System.out.println("Exiting CourseDAO.getCourseDetails(" + courseCode
				+ ")");
		return courseVO;
	}
}
