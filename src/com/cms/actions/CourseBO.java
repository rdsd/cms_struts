package com.cms.actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cms.constants.ErrorConstants;
import com.cms.dao.CourseDAO;
import com.cms.exceptions.CMSBusinessException;
import com.cms.exceptions.CMSException;
import com.cms.vo.CourseVO;

public class CourseBO {

	private CourseDAO courseDAO = null;
	private static final Logger LOG = Logger.getLogger(CourseBO.class);

	public CourseBO() {
		courseDAO = new CourseDAO();
	}

	public boolean addCourse(CourseVO courseVO) throws CMSBusinessException,
			CMSException {
		// System.out.println("In CourseBO.addCourse(" + courseVO + ") Method");
		LOG.info("In CourseBO.addCourse(" + courseVO + ") Method");
		boolean courseAddedFlag = false;
		boolean courseExistenceFlag = courseDAO
				.checkCourseCodeExistence(courseVO.getCourseCode());
		if (!courseExistenceFlag) {
			// validate the courseCode against the business logic- course code
			// needs to start with cms followed by 100 - 200
			boolean courseCodeValidFlag = validateCourseCode(courseVO
					.getCourseCode());
			if (!courseCodeValidFlag) {
				throw new CMSBusinessException(
						ErrorConstants.COURSE_NAMING_CONVENTION);
			}
			// calculate course fee and add the course
			int courseFee = calculateFee(courseVO);
			courseVO.setCourseFee(courseFee);
			courseAddedFlag = courseDAO.addCourse(courseVO);
		} else {
			throw new CMSBusinessException("Course code "
					+ courseVO.getCourseCode() + " already exists");

		}

		return courseAddedFlag;
	}

	private boolean validateCourseCode(String courseCode) {
		/*
		 * System.out .println("In CourseBO.validateCourseCode(" + courseCode +
		 * ")");
		 */
		LOG.info("In CourseBO.validateCourseCode(" + courseCode + ")");
		Pattern pattern = Pattern.compile("^cms((1[0-9][0-9])|200)");
		Matcher matcher = pattern.matcher(courseCode);
		boolean matchedFlag = matcher.find();
		/*
		 * System.out.println("Exiting CourseBO.validateCourseCode(" +
		 * courseCode + "): " + matchedFlag);
		 */
		LOG.info("Exiting CourseBO.validateCourseCode(" + courseCode + "): "
				+ matchedFlag);
		return matchedFlag;
	}

	private int calculateFee(CourseVO courseVO) {
		LOG.info("In CourseBO.calculateFee(" + courseVO + ")");
		int courseFee = 0;

		String courseDuration = courseVO.getCourseDuration();
		String courseType = courseVO.getCourseType();

		if (courseType.equalsIgnoreCase("classroom")) {
			if (Pattern.matches("D1", courseDuration)) {
				courseFee = 3000;
			} else if (Pattern.matches("D2", courseDuration)) {
				courseFee = 4000;
			} else {
				courseFee = 7000;
			}

		} else {
			if (Pattern.matches("D1", courseDuration)) {
				courseFee = 1000;
			} else if (Pattern.matches("D2", courseDuration)) {
				courseFee = 2000;
			} else {
				courseFee = 3000;
			}
		}
		/*
		 * System.out.println("Exiting CourseBO.calculateFee(" + courseVO +
		 * ") = " + courseFee);
		 */
		LOG.info("Exiting CourseBO.calculateFee(" + courseVO + ") = "
				+ courseFee);
		return courseFee;
	}

	public CourseVO getCourseDetails(String courseCode) throws CMSException,
			CMSBusinessException {
		LOG.info("In CourseBO.getCourseDetails()");
		CourseVO courseVO = courseDAO.getCourseDetails(courseCode);

		if (courseVO == null) {
			throw new CMSBusinessException(ErrorConstants.COURSE_NOT_FOUND);
		}
		LOG.info("Exiting CourseBO.getCourseDetails()");
		return courseVO;
	}

	public boolean checkCourseCodeExistence(String courseCode)
			throws CMSException {
		boolean courseExistenceFlag = courseDAO
				.checkCourseCodeExistence(courseCode);
		return courseExistenceFlag;
	}

}
