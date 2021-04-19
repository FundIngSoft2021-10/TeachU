<?php

require_once 'include/DB_Functions.php';
$db = new DB_Functions();

$Nusuario = $_GET['Nusuario'];

$db->existeUsuarioNusuario($Nusuario);



?>