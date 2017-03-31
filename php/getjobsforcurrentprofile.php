
<?php
$con = new mysqli("192.175.117.181", "dbuser3", "8DRxBBXfdv", "db3", "3306");
$response = array();

if($con->connect_errno){
    $response['error'] = "Error: Unable to connect to MySQL.";
    return(json_encode($response));
}
	
$request = file_get_contents('php://input');
$input = json_decode($request);
$currentProfile = $input->currentProfile;
	
	
$query = "SELECT * FROM jobs WHERE jobs.typeofjob='$currentProfile'"; 	

$result = $con->query($query);
	
	
	
if($result->num_rows > 0){ 
    while($row = $result->fetch_array(MYSQLI_ASSOC)){
        $response['jobs'][] = $row;
    }
  
}
else{
    $response['error'] = "No jobs found.";
}

echo json_encode($response);
$con->close(); 	 
   
?>
