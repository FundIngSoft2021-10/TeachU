<?php

class DB_Functions{
	private $con;
	
	function __construct(){
		require_once 'DB_Connect.php';
		$db = new DB_Connect();
		$this->con = $db->conectar();
	}
	function __destruct() {
         
    }
	
	
	public function getUsuarioPorUsuarioContra($correo, $contra){
		$query = $this->con->prepare("SELECT * FROM Usuario WHERE email = ?");
		
	}
	
	
	public function existeUsuario($correo){
		$query = $this->con->prepare("SELECT * FROM Usuario WHERE email = ?");
		$query->bind_param("s", $correo);
		$query->execute();
		$query->store_result();
		
		if($query->num_rows > 0){
			
			$query->close();
			return true;
		}
		else{
			$query->close();
			return false;
		}
		
	}
	
	public function insertarUsuario($nombre, $apellido,$contrasena,$correo,$nusuario, $id_usuario){
		$query = $this->con->prepare("INSERT INTO Usuario (Nombre, Apellido, contrasena, CorreoInst, Nusuario, Id_Usuario) VALUES (?, ?, ?, ?, ?, ?)");
		$query->bind_param("sssssi", $nombre, $apellido,$contrasena,$correo,$nusuario, $idusuario);
		$resultado = $query->execute();
		$query->close();
		
		if($resultado){
			$stmt = $this->con->prepare("SELECT * FROM Usuario WHERE correo = ?");
            $stmt->bind_param("s", $email);
			$stmt->execute();
			$usuario = $stmt->get_result()->fetch_assoc();
			$stmt->close();
			
			return $user;
		}
		else{
			return false;
		}
	}
}

?>