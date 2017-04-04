<?php
    $con = new mysqli("192.175.117.181", "dbuser3", "8DRxBBXfdv", "db3", "3306");
	$response = array();

	if($con->connect_errno){
		$response['error'] = "Error: Unable to connect to MySQL.";
		return(json_encode($response));
	}
	
	
	$request = file_get_contents('php://input');
	$input = json_decode($request);
	$emailProvider = $input->emailProvider;
	$displayNameSeeker = $input->displayNameSeeker;
	$typeOfJob = $input->typeOfJob; 
	

    $query = "
	UPDATE applications 
	INNER JOIN users ON applications.applicant_id = users.id
	INNER JOIN jobs ON applications.posterEmail = jobs.posterEmail
	SET applications.status='accepted'
	WHERE jobs.posterEmail = '$emailProvider' AND jobs.typeofjob = '$typeOfJob'
	AND users.displayname = '$displayNameSeeker';
	"; 	
    $result = $con->query($query);
	
    $con->close();
 
   
?>