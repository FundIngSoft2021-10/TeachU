<?php
require_once 'include/DB_Connect.php';
$dd = new DB_Connect();
$con = $dd->conectar();
if (!$con) {
  die("Connection failed: " . mysqli_connect_error());
}
	
$query = $con->prepare("select * from Disponibilidad");

$sql = "select * from Disponibilidad";
$result = mysqli_query($con, $sql);
$rows = array();
	
	if(mysqli_num_rows($result) > 0){
		while($r = mysqli_fetch_assoc($result)){
			array_push($rows, $r);
		}
		print json_encode($rows);
	}
	
	
?>