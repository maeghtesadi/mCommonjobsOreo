<?php
$con = new mysqli("192.175.117.181", "dbuser3", "8DRxBBXfdv", "db3", "3306");
$response = array();

if($con->connect_errno){
    $response['error'] = "Error: Unable to connect to MySQL.";
    return(json_encode($response));
}

$query = "SELECT name FROM skill";
$result = $con->query($query);

if($result->num_rows > 0){
    $rows = array();
    while($row = $result->fetch_array(MYSQLI_ASSOC)){
        $rows[] = $row['name'];
    }
    $response['result'] = $rows;
}
else{
    $response['error'] = "Error: No skills found.";
}

echo json_encode($response);
$con->close();
?>
