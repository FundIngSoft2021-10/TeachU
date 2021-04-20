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
public function existeUsuarioNusuario($Nusuario){
		$query = $this->con->prepare("SELECT * FROM Usuario WHERE Nusuario = ?");
		$query->bind_param("s", $Nusuario);
		$query->execute();
		$usuario = $query->get_result()->fetch_assoc();
		$query->close();
		return $usuario; 
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
	public function insertarEstudiante($id, $carrera){
		$query = $this->con->prepare("INSERT INTO Estudiante (Id_Usuario, IdCarrera) VALUES (?, ?)");
		$query->bind_param("ii", $id, $carrera);
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
	public function insertarTutor($id, $cedula, $tipodoc,$descripcion,$ranking){
		$query = $this->con->prepare("INSERT INTO Tutor (Id_Usuario, Cedula, tipoDoc ,Descripcion, Ranking) VALUES (?, ?, ?, ?, ?)");
		$query->bind_param("isssi", $id, $cedula, $tipodoc,$descripcion,$ranking);
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
	
	
	public function updateUsuario($nombre, $apellido, $correo, $Nusuario, $id ){
		$query = $this->con->prepare("UPDATE Usuario SET Nombre = ?, Apellido = ?, CorreoInst = ?, Nusuario = ? WHERE Id_Usuario = ?");
		$query->bind_param("ssssi", $nombre, $apellido, $correo, $Nusuario, $id);
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