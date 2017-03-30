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
	
   
$query = "SELECT DISTINCT profiles.typeofprofile FROM profiles INNER JOIN
users_profiles ON profiles.id = users_profiles.profileid  
INNER JOIN users 
ON users.id = users_profiles.userid
WHERE users.email='$email'"; 	

$result = $con->query($query);

if($result->num_rows > 0){ 
    while($row = $result->fetch_array(MYSQLI_ASSOC)){
        $response['Profile'][] = $row;
    }
  
}
else{
    $response['error'] = "No profiles found.";
}

echo json_encode($response);
$con->close(); 
   
?>
