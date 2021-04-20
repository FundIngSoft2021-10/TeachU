<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
$id_usuario = "1";
$nombre = "Luffy";
$apellido = "Mango";
$nUsuario = "Mugiwara";
$correo = "ElIngenieroCrack@DiosDeLaIngenieria.com" ;
$response = array("error" => FALSE);

		$usuario = $db->updateUsuario($nombre, $apellido, $correo, $nUsuario, $id_usuario);
		if($usuario){
			$response["error"] = FALSE;
			$response["Usuario"]["Id_Usuario"] = $usuario["Id_Usuario"];
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
	

?>