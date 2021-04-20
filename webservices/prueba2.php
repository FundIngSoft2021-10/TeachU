<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
$id_usuario = "13";
$id_clase = "4";

$response = array("error" => FALSE);

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
	

?>