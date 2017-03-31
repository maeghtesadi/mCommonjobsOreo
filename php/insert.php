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
	$firstName = $input->firstname;
	$lastName = $input->lastname;
	$type = $input->typeofuser; 
	
   $query = "INSERT INTO users (id, firstName, lastName, displayName, email, phoneNumber, type) VALUES (NULL,'$firstName','$lastName','$firstName', '$email','None', '$type')"; 	
   $result = $con->query($query);
   
   
   mysqli_close($con);
?>