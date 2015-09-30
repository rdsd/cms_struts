package com.cms.actions;

import com.cms.constants.ErrorConstants;
import com.cms.dao.CourseDAO;
import com.cms.exceptions.CMSBusinessException;
import com.cms.exceptions.CMSException;
import com.cms.vo.CourseVO;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class RetrieveAction extends ActionSupport {
	private String courseCode;
	private CourseDAO courseDAO = null;
	private CourseVO courseVO;

	public CourseVO getCourseVO() {
		return courseVO;
	}

	public void setCourseVO(CourseVO courseVO) {
		this.courseVO = courseVO;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public RetrieveAction() {
		courseDAO = new CourseDAO();
	}

	String outputFileName = null;

	public String getCourseDetails() {
		LOG.info("In CourseBO.getCourseDetails()");
		try {
			courseVO = courseDAO.getCourseDetails(courseCode);
		} catch (CMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

		if (courseVO == null) {
			outputFileName = "Error";
			try {
				throw new CMSBusinessException(ErrorConstants.COURSE_NOT_FOUND);
			} catch (CMSBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			outputFileName = "courseVO";
		}
		LOG.info("Exiting CourseBO.getCourseDetails()");
		return outputFileName;
	}
}
