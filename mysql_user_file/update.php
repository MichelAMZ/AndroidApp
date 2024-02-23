
<?php
#--- Ajout d'utilisateur dans la bdd #

#On inclut le fichier db_connect.php
include ("db_connect.php");

# recoit les resultats sous format json_decode
$rsponse=array();

# On reçoit l'id, nom, l'age de l'utilisatuon
if(isset($_GET["id"]) && isset($_GET["nom"]) && isset($_GET["age"]) )
{
	$id=$_GET["id"];
	$nom=$_GET["nom"];
	$age=$_GET["age"];
	
	$req=mysqli_query($cnx, " update user set name='$nom', age='$age' where id='$id' " );
		
	if($req)
	{
		$response["success"]=1;
		$response["message"]="update successfully !";
		
		#reponse fournie sous form json_decode
		echo json_encode($response);
	} 
	else
	{
		$response["success"]=0;
		$response["message"]="Request error !";
		
		#reponse fournie sous form json_decode
		echo json_encode($response);
	}
}
else
{
		$response["success"]=0;
		$response["message"]="Required filed is missing !";
		
		#reponse fournie sous form json_decode
		echo json_encode($response);
}




?>