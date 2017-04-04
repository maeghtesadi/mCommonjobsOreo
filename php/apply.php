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
	$emailSeeker = $input->emailSeeker;
	$typeOfJob = $input->typeofjob; 
	$description = $input->description;
	
   
    $query = "INSERT INTO applications (id, applicant_id,  job_id, posterEmail ,status) 
			  VALUES (NULL, (SELECT id FROM users WHERE email='$emailSeeker'), (SELECT id FROM jobs WHERE typeofjob='$typeOfJob' AND description='$description'), 
			  '$emailProvider','pending')"; 	
    $result = $con->query($query);
	
    $con->close();
 
   
?>