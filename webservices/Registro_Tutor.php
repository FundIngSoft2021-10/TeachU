<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
if (isset($_POST["id_usuario"]) && isset($_POST["cedula"]) && isset($_POST["tipodoc"]) && isset($_POST["descripcion"])&& isset($_POST["ranking"])){
 
    // receiving the post params
    $id_usuario = $_POST["id_usuario"];
	$cedula = $_POST["cedula"];
	$tipodoc = $_POST["tipodoc"];
	$descripcion = $_POST["descripcion"];
	$ranking = $_POST["ranking"];

	

	
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
	
} 
else {
    $response["error"] = TRUE;
    $response["ms_error"] = "Parametro faltante";
    echo json_encode($response);
}
	
	

	
?>