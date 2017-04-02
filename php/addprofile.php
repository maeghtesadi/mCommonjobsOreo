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
$profile = $input->profile;
	 

$query = "INSERT INTO users_profiles (userid, profileid) select users.id, profiles.id from users, profiles where users.email='$email' and profiles.typeofprofile='$profile'";
$result = $con->query($query);



$con->close();
   
   

   
?>
