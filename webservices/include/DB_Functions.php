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
	public function insertarTutorxClase($id_usuario, $id_clase){
		$query = $this->con->prepare("INSERT INTO TutorXClase(Tutor_Id_Usuario,Clases_idClase) VALUES (?, ?)");
		$query->bind_param("ii", $id_usuario, $id_clase);
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
	
	public function existeUsuarioEstudiante($id){
		$query = $this->con->prepare("SELECT * FROM Estudiante WHERE Id_Usuario = ?");
		$query->bind_param("s", $id);
		$query->execute();
		$usuario = $query->get_result()->fetch_assoc();
		$query->close();
		return $usuario; 
	}
	
	public function existeUsuarioTutor($id){
		$query = $this->con->prepare("SELECT * FROM Tutor WHERE Id_Usuario = ?");
		$query->bind_param("s", $id);
		$query->execute();
		$usuario = $query->get_result()->fetch_assoc();
		$query->close();
		return $usuario; 
	}

public function insertarDisxTutor($disponibilidad, $tutor){
		$query = $this->con->prepare("INSERT INTO DisponibilidadxTutor(Tutor_Id_Usuario,IdDisponibilidad) VALUES (?, ?)");
		$query->bind_param("ii", $disponibilidad, $tutor);
		$resultado = $query->execute();
		$query->close();
		
		if($resultado){
			$stmt = $this->con->prepare("SELECT * FROM DisponibilidadxTutor WHERE Tutor_Id_Usuario = ?");
            $stmt->bind_param("i", $tutor);
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

	public function updateUxC($Nusuario, $contra ){
        $query = $this->con->prepare("UPDATE Usuario SET contrasena = ? WHERE Nusuario = ?");
        $query->bind_param("ss", $Nusuario, $contra);
        $resultado = $query->execute();
        $query->close();

        if($resultado){
            $stmt = $this->con->prepare("SELECT * FROM Usuario WHERE Nusuario = ?");
            $stmt->bind_param("s", $Nusuario);
            $stmt->execute();
            $usuario = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $usuario;
        }
        else{
            return false;
        }

    }
	
	public function insertarTutoria($IdEstudiante, $IdTutor, $IdTutoria, $Fecha, $Duracion, $idClase, $Precio){
		$query = $this->con->prepare("INSERT INTO Tutoria (IdEstudiante, IdTutor, IdTutoria ,Fecha, Duracion, idClase, Precio) VALUES (?, ?, ?, ?, ?, ?, ?)");
		$query->bind_param("iiisiid", $IdEstudiante, $IdTutor, $IdTutoria,$Fecha,$Duracion, $idClase, $Precio);
		$resultado = $query->execute();
		$query->close();
		
		if($resultado){
			$stmt = $this->con->prepare("SELECT * FROM Tutoria WHERE IdTutoria = ?");
            $stmt->bind_param("i", $IdTutoria);
			$stmt->execute();
			$Tutoria = $stmt->get_result()->fetch_assoc();
			$stmt->close();
			
			return $Tutoria;
		}
		else{
			return false;
		}
	}


	public function eliminarTutoria($id){
		$query = $this->con->prepare("DELETE FROM Tutoria WHERE IdTutoria = ?");
		$query->bind_param("i", $id);
		$query->execute();
		$query->close();
		
	}
}




?>