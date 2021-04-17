<?php
	$url = "teachu.cgcnxzpwojtb.us-east-2.rds.amazonaws.com:3306";
	$db = "teachU";
	$usuario = "godzilla";
	$contra = "teachu1234";
	
	$con = mysqli_connect($url, $usuario, $contra, $db);
	if($con->connect_error){
		die("......" . $con->connect_error);
	}
	
	$sql = "SELECT * FROM Carreras";
	$result = mysqli_query($con, $sql);
	$rows = array();
	
	if(mysqli_num_rows($result) > 0){
		while($r = mysqli_fetch_assoc($result)){
			array_push($rows, $r);
		}
		print json_encode($rows);
	}
	
	
	
	mysqli_close($con);
?>