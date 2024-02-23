
<?php
#--- Ajout d'utilisateur dans la bdd #

#On inclut le fichier db_connect.php
include ("db_connect.php");

$response=array();

# On reçoit l'id, nom, l'age de l'utilisatuon
if( isset($_GET["id"]) )
{
	$id=$_GET["id"];
		
	$req=mysqli_query($cnx, " delete from user where id='$id' " );
			
	if($req)
	{
		$response["success"]=1;
		$response["message"]="delete successfully !";
		
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