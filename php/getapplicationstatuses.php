
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





$query = "
SELECT jobs.typeofjob, applications.status 
FROM applications INNER JOIN jobs ON applications.job_id = jobs.id
INNER JOIN users
on applications.applicant_id = users.id
WHERE users.email = '$email';
"; 	

$result = $con->query($query);
	
	
	
if($result->num_rows > 0){ 
    while($row = $result->fetch_array(MYSQLI_ASSOC)){
        $response['applications'][] = $row;
    }
  
}
else{
    $response['error'] = "No applications found.";
}

echo json_encode($response);
$con->close(); 	 
   
 
   
   
?>

