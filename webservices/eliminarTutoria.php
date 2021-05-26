<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
if (isset($_POST["IdTutoria"])){
 
    // receiving the post params
    $id = $_POST["IdTutoria"];
	
	$db->eliminarTutoria($id);

} 
else {
    $response["error"] = TRUE;
    $response["ms_error"] = "Parametro faltante";
    echo json_encode($response);
}
	
	

	
?>