<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
$response = array("error" => FALSE);
if (isset($_POST["nUsuario"]) && isset($_POST["contrasena"])){
	$nusuario = $_POST["nUsuario"];
	$contra = $_POST["contrasena"];
	
	$usuario = $db->updateUxC($nusuario, $contra);
	if($usuario){
			$response["error"] = FALSE;
			$response["Usuario"]["Nombre"] = $usuario["Nombre"];
			$response["Usuario"]["Apellido"] = $usuario["Apellido"];
			$response["Usuario"]["CorreoInst"] = $usuario["CorreoInst"];
			$response["Usuario"]["nUsuario"] = $usuario["nUsuario"];
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