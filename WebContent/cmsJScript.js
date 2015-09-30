function checkIfEmpty()
{
	//alert("In javaScript.checkIfEmpty()");
	//console.log("In javaScripts.checkIfEmpty()");
	resetErrorMessages();
	var courseCode = document.getElementById("courseCode").value;
	console.log(courseCode);
	var courseName = document.getElementById("courseName").value;
	var courseDescription = document.getElementById("courseDescription").value;
	var noOfParticipants = document.getElementById("noOfParticipants").value;
	var courseType = document.getElementById("courseType").value;
	var courseDuration = document.getElementById("courseDuration").value;
	var formValidation = true;
	if(courseCode == null || courseCode == ''){
		//alert("In javaScript.checkIfEmpty(courseCode)");
		document.getElementById("courseCodeError").innerHTML = "Course code cannot be empty";
		formValidation = false;
	}
	else{
		//check for cms naming convention
		//alert("In javaScript.checkIfEmpty(courseCode) = 1");
		//#####################################WHY THIS DOES NOT WORK ?#################################################/
		//var courceCodePattern = "/^(cms)((1[0-9][0-9])|200)$/";
		//var validCourseCodeFlag = courseCodePattern.test(courseCode);
		var validCourseCodeFlag = /^(cms)((1[0-9][0-9])|200)$/.test(courseCode);
		if(validCourseCodeFlag === false){
			document.getElementById("courseCodeError").innerHTML = "Course code should follow CMS naming convention";
			formValidation = false;
		}
	}
	//validate course name
	if(courseName == null || courseName == ''){
		document.getElementById("courseNameError").innerHTML = "Course name cannot be empty";
		formValidation = false;
	}
	else{
		if(isNaN(courseName) == false){
			document.getElementById("courseNameError").innerHTML = "Course name cannot be number";
		}
			
	}
	//validate course description
	//######################################THIS IS NOT WORKING.WHY?###########################################
	if(courseDescription == null || courseDescription == ''){
		document.getElementById("courseDescriptionError").innerHTML = "Course description cannot be empty";
		formValidation = false;
	}
	else{
		if(isNaN(courseDescription) == false){
			document.getElementById("courseDescription").innerHTML = "Course description cannot be number";
		}
	}
	//validate number of participants		
	if(noOfParticipants == null || noOfParticipants == ''){
		document.getElementById("noOfParticipantsError").innerHTML = "Number of Participants cannot be empty";
		formValidation = false;
	}
	else{
		//check for number values only
		if(isNaN(noOfParticipants) === true)
			{
			document.getElementById("noOfParticipantsError").innerHTML = "Number of participants should be a number";
			console.log("number of participants should be a number");
			formValidation = false;
			}
	}
	if(courseType == null || courseType == ''){
		document.getElementById("courseTypeError").innerHTML = "Course type cannot be empty";
		formValidation = false;
	}
	if(courseDuration == null || courseDuration == ''){
		document.getElementById("courseDurationError").innerHTML = "Course duration cannot be empty";
		formValidation = false;
	}
	//Do we set the previous error message that Servlet sent to empty so that 
	//we do not get Server error and client error at the same time
	//################################IS THIS RIGHT?#####################################################
	document.getElementById("errorMessage").innerHTML = ""; 
	return formValidation;
}

function resetErrorMessages()
{
	//console.log("In resetErrorMessages");
	document.getElementById("courseCodeError").innerHTML = "";
	document.getElementById("courseNameError").innerHTML = "";
	document.getElementById("courseDescriptionError").innerHTML = "";
	document.getElementById("noOfParticipantsError").innerHTML = "";
	document.getElementById("courseDurationError").innerHTML = "";
	document.getElementById("courseTypeError").innerHTML = "";
}