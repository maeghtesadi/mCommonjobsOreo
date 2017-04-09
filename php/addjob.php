<?php
    $con = new mysqli("192.175.117.181", "dbuser3", "8DRxBBXfdv", "db3", "3306");
	$response = array();

	if($con->connect_errno){
		$response['error'] = "Error: Unable to connect to MySQL.";
		return(json_encode($response));
	}
 

	$request = file_get_contents('php://input');
	$input = json_decode($request);
	$email = $input->email;
	$typeofjob = $input->typeofjob;
	$description = $input->description;
  	$location = $input->location;
	$duration = $input->duration;

    $query = "INSERT INTO jobs (id, posterEmail, typeofjob, description, status, location, duration, conditions) VALUES (NULL,'$email','$typeofjob','$description', 'pending','$location','$duration','N/A')"; 	
    $result = $con->query($query);
   

    $con->close();
 
?>