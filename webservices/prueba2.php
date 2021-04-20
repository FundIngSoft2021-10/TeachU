<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
$id_usuario = "2";
$cedula = "6567";
$tipodoc = "TI";
$descripcion = "soy bueno";
$ranking = "4";
$response = array("error" => FALSE);

		$usuario = $db->insertarTutor($id_usuario,$cedula,$tipodoc,$descripcion,$ranking);
		if($usuario){
			$response["error"] = FALSE;
			$response["Tutor"]["Id_Usuario"] = $usuario["Id_Usuario"];
			$response["Tutor"]["Cedula"] = $usuario["Cedula"];
			$response["Tutor"]["tipoDoc"] = $usuario["tipoDoc"];
			$response["Tutor"]["Descripcion"] = $usuario["Descripcion"];
			$response["Tutor"]["Ranking"] = $usuario["Ranking"];
			echo json_encode($response);
		}
		else{
			$response["error"] = TRUE;
			$response["ms_error"] = "Error";
			echo json_encode($response);
		}
	

?>