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
		$query = $this->con->prepare("SELECT * FROM Usuario WHERE CorreoInst = ?");
		
	}
	
	
	public function existeUsuario($correo){
		$query = $this->con->prepare("SELECT * FROM Usuario WHERE CorreoInst = ?");
		$query->bind_param("s", $correo);
		$query->execute();
		$query->store_result();
		
		if($query->num_rows > 0){ // validacion de numero de filas 
			
			$query->close();
			return true;
		}
		else{
			$query->close();
			return false;
		}
		
	}
	public function calcularId(){
		$query = $this->con->prepare("select MAX(Id_Usuario)+1 as nid from Usuario");
		$query->execute();
		$n_id = $query->get_result()->fetch_assoc();
		return $n_id;
		
	}
	
	public function insertarUsuario($nombre, $apellido,$contrasena,$correo,$nusuario, $id_usuario){
		$query = $this->con->prepare("INSERT INTO Usuario (Nombre, Apellido, contrasena, CorreoInst, Nusuario, Id_Usuario) VALUES (?, ?, ?, ?, ?, ?)");
		$query->bind_param("sssssi", $nombre, $apellido,$contrasena,$correo,$nusuario, $id_usuario);
		$resultado = $query->execute();
		$query->close();
		
		if($resultado){
			$stmt = $this->con->prepare("SELECT * FROM Usuario WHERE CorreoInst = ?");
            $stmt->bind_param("s", $correo);
			$stmt->execute();
			$usuario = $stmt->get_result()->fetch_assoc();
			$stmt->close();
			
			return $usuario;
		}
		else{
			return false;
		}
	}
}

?>