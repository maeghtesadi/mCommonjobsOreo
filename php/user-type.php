<?php
$con = mysqli_connect("192.175.117.181", "dbuser3", "8DRxBBXfdv", "db3", "3306");
$response = array();

if($con->connect_errno){
    $response['error'] = "Error: Unable to connect to MySQL.";
    return(json_encode($response));
}

$request = file_get_contents('php://input');
$input = json_decode($request);
$email = $input->email;

$query = "SELECT type FROM user WHERE email='$email'";
$result = $con->query($query);

if($result->num_rows > 0){
    $response['result'] = $result->fetch_assoc();
}
else{
    $response['error'] = 'Error: User type not found.';
}

echo json_encode($response);
$con->close();
?>
