<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
if (isset($_POST["id_usuario"]) && isset($_POST["id_clase"])){
 
    // receiving the post params
    $id_usuario = $_POST["id_usuario"];
	$id_clase = $_POST["id_clase"];

	

	
		$usuario = $db->insertarTutorxClase($id_usuario,$id_clase);
		if($usuario){
			$response["error"] = FALSE;
			$response["TutorXClase"]["Tutor_Id_Usuario"] = $usuario["Tutor_Id_Usuario"];
			$response["TutorXClase"]["Clases_idClase"] = $usuario["Clases_idClase"];
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