<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
if (isset($_POST["id_usuario"]) && isset($_POST["id_Carrera"])){
 
    // receiving the post params
    $id_usuario = $_POST["id_usuario"];
	$id_carrera = $_POST["id_Carrera"];

	

	
		$usuario = $db->insertarEstudiante($id_usuario,$id_carrera);
		if($usuario){
			$response["error"] = FALSE;
			$response["Estudiante"]["Id_Usuario"] = $usuario["Id_Usuario"];
			$response["Estudiante"]["IdCarrera"] = $usuario["IdCarrera"];
			echo json_encode($response);
		}
		else{
			$response["error"] = TRUE;
			$response["ms_error"] = "Error";
			echo json_encode($response);
		}
	
} 
else {
    $response["error"] = TRUE;
    $response["ms_error"] = "Parametro faltante";
    echo json_encode($response);
}
	
	

	
?>