
<?php
#--- Ajout d'utilisateur dans la bdd #

#On inclut le fichier db_connect.php
include ("db_connect.php");

	$response=array();


	$req=mysqli_query($cnx, " select * from user " );
			
	if( mysqli_num_rows($req) > 0)
	{
		#table provisoire
		$temp=array();
		$response["users"]=array();
		
		while( $cur=mysqli_fetch_array($req) )
		{
			$temp["id"]=$cur["id"];
			$temp["nom"]=$cur["Name"];
			$temp["age"]=$cur["Age"];
			
			array_push($response["users"], $temp );
		}
	
		$response["success"]=1;
		
		#reponse fournie sous form json_decode
		echo json_encode($response);
	} 
	else
	{
		$response["success"]=0;
		$response["message"]="No data found !";
		
		#reponse fournie sous form json_decode
		echo json_encode($response);
	}


?>