<?php

$cnx=mysqli_connect("localhost", "root", ""); # Définit les utilisateurs
if(!$cnx)
{
		echo "Error de connexion au serveur. ";
}

# La connexion à la bdd
$db=mysqli_select_db($cnx, "usermanager");
if(!$db)
{
	echo "Erreur de connexion à la base de données";
}

?>