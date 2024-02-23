
<?php
	#--- Ajout d'utilisateur dans la bdd #

	#On inclut le fichier db_connect.php
	include ("db_connect.php");

	# recoit les resultats sous format json_decode
		$response=array();
	
	# ( isset($_GET["nom"]) && isset($_GET["age"]) && isset($_GET["email"]) && isset($_GET["codeUser"]) )
	
	if( isset($_GET["nom"]) && isset($_GET["age"]) )
	{
		$nom=$_GET["nom"];
		$age=$_GET["age"];
		$email=$_GET["email"];
		$code=$_GET["codeUser"];
		
			
		$req=mysqli_query($cnx," insert into user(Name, Age, email, codeUser) values('$nom', '$age', '$email', '$code')");
		
		if($req)
		{
			$response["success"]=1;
			$response["message"]="inserted !";
						

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