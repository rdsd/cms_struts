package com.cms.actions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cms.dao.CourseDAO;
import com.cms.exceptions.CMSBusinessException;
import com.cms.exceptions.CMSException;
import com.cms.vo.CourseVO;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AddCourseAction extends ActionSupport {
	private CourseDAO courseDAO;
	private CourseVO courseVO;

	public CourseVO getCourseVO() {
		return courseVO;
	}

	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}

	public AddCourseAction() {
		courseDAO = new CourseDAO();
	}

	public String addCourse() {
		// System.out.println("In CourseBO.addCourse(" + courseVO + ") Method");
		LOG.info("In CourseBO.addCourse(" + courseVO + ") Method");
		boolean courseExistenceFlag;
		String outputFileName = null;
		try {
			courseExistenceFlag = courseDAO.checkCourseCodeExistence(courseVO
					.getCourseCode());
			if (!courseExistenceFlag) {
				// validate the courseCode against the business logic- course
				// code
				// needs to start with cms followed by 100 - 200
				// boolean courseCodeValidFlag = validateCourseCode(courseVO
				// .getCourseCode());

				// calculate course fee and add the course
				int courseFee = calculateFee(courseVO);
				courseVO.setCourseFee(courseFee);
				boolean courseAddedFlag = courseDAO.addCourse(courseVO);
				outputFileName = "Success";
			} else {
				try {
					throw new CMSBusinessException("Course code "
							+ courseVO.getCourseCode() + " already exists");
				} catch (CMSBusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					outputFileName = "input";
					addFieldError("courseVO.courseCode", e.getMessage());
				}

			}
		} catch (CMSException e) {
			outputFileName = "Error";
			e.printStackTrace();
		}

		return outputFileName;
	}

	@Override
	public void validate() {
		validateCourseCode(courseVO.getCourseCode());
	}

	private boolean validateCourseCode(String courseCode) {

		System.out
				.println("In CourseBO.validateCourseCode(" + courseCode + ")");

		LOG.info("In CourseBO.validateCourseCode(" + courseCode + ")");
		Pattern pattern = Pattern.compile("^cms((1[0-9][0-9])|200)");
		Matcher matcher = pattern.matcher(courseCode);
		boolean matchedFlag = matcher.find();
		/*
		 * System.out.println("Exiting CourseBO.validateCourseCode(" +
		 * courseCode + "): " + matchedFlag);
		 */
		if (!matchedFlag) {
			addFieldError("courseVO.courseCode",
					"Course code must match cms naming convention");
		}
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

}
