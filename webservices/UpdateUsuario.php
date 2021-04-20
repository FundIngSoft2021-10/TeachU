<?php
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
$response = array("error" => FALSE);
if (isset($_POST["nombre"]) && isset($_POST["apellido"]) && isset($_POST["correo"]) isset($_POST["nUsuario"])&& isset($_POST["id_usuario"])){
	$nombre = $_POST["nombre"];
	$apellido = $_POST["apellido"];
    $correo = $_POST["correo"];
	$nusuario = $_POST["nUsuario"];
	$id_usuario = $_POST["id_usuario"];
	
	$usuario = $db->updateUsuario($nombre, $apellido, $correo, $nusuario, $id_usuario);
	if($usuario){
			$response["error"] = FALSE;
			$response["Usuario"]["Nombre"] = $usuario["Nombre"];
			$response["Usuario"]["Apellido"] = $usuario["Apellido"];
			$response["Usuario"]["CorreoInst"] = $usuario["CorreoInst"];
			$response["Usuario"]["Nusuario"] = $usuario["Nusuario"];
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