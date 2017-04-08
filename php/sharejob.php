<?php
    $con = new mysqli("192.175.117.181", "dbuser3", "8DRxBBXfdv", "db3", "3306");
	$response = array();

	if($con->connect_errno){
		$response['error'] = "Error: Unable to connect to MySQL.";
		return(json_encode($response));
	}
	
	
	$request = file_get_contents('php://input');
	$input = json_decode($request);
	$emailRecipient = $input->emailRecipient;
	$emailSender = $input->emailSender;
	$typeOfJob = $input->typeofjob; 
	$description = $input->description;
	$query = "SELECT * from users WHERE email='$emailRecipient'";
	$result = $con->query($query);
	
	if($result->num_rows > 0){
    $query = "INSERT INTO sharedjobs (id, emailSender, recipient_id, job_id) VALUES 
			 (NULL, '$emailSender', (SELECT id FROM users WHERE email='$emailRecipient'), 
			 (SELECT id from jobs WHERE typeofjob='$typeOfJob' AND description='$description')) "; 	
    $result = $con->query($query);
	$response['result'] = "Job shared!";
    echo json_encode($response);
    $con->close();
	}else{
		 $response['error'] = "Error: No email found.";
		 echo json_encode($response);
		 $con->close();
	}
 
   
?>