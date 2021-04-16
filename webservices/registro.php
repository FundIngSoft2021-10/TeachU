<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
 
if (isset($_POST['nombre']) && isset($_POST['apellido']) && isset($_POST['correo'])) && isset($_POST['contrasena'])&& isset($_POST['nUsuario'])&& isset($_POST['id_usuario']){
 
    // receiving the post params
    $nombre = $_POST['nombre'];
	$apellido = $_POST['apellido'];
    $correo = $_POST['correo'];
    $password = $_POST['contrasena'];
	$nusuario = $_POST['nusuario'];
	$id_usuario = $_POST['id_usuario'];
	
	if($db->existeUsuario($correo)){
		$response["error"] = TRUE;
        $response["error_msg"] = "Usuario ya existe con correo  " . $email;
		echo json_encode($response);
	}
	else{
		
	}
	
	

	
?>