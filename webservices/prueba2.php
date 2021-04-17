<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
$nombre="daniel";
$apellido="mango";
$correo="pera";
$contrasena="1234";
$nusuario="manguito";
$id_usuario="17";
$response = array("error" => FALSE);
if($db->existeUsuario($correo)){
		$response["error"] = TRUE;
        $response["error_msg"] = "Usuario ya existe con correo  " . $correo;
		echo json_encode($response);
	}
	else{
		$usuario = $db->insertarUsuario($nombre, $apellido,$contrasena,$correo,$nusuario, $id_usuario);
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

?>