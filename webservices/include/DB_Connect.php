<?php

class DB_Connect{
	private $con;
	
	public function conectar(){
		require_once 'include/Config.php';
		
		$this->con = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_DATABASE);
		
		return $this->con;
	}
	
}

?>