
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
	
$query = "SELECT * FROM jobs INNER JOIN sharedjobs ON jobs.id=sharedjobs.job_id 
          INNER JOIN users on users.id = sharedjobs.recipient_id
		  WHERE users.email = '$email'";
		  
$result = $con->query($query);

if($result->num_rows > 0){ 
    $rows = array();
    while($row = $result->fetch_array(MYSQLI_ASSOC)){
        $response['jobs'][] = $row;
    }
}
else{
    $response['error'] = "Error: No jobs found.";
}

echo json_encode($response);
$con->close();

  
   
?>

