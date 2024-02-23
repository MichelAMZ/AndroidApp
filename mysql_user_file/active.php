<?php

	#On inclut le fichier db_connect.php
	include ("db_connect.php");

	# recoit les resultats sous format json_decode
		$response=array();

	if( isset($_POST["email"]) && isset($_POST["code"]) )
	{
		$stmt=$cnx->prepare("select id from user where email=? and state=?");
		$stmt = bind_param("ss", $_GET["email"], $_GET["code"] );
		$stmt->execute();
			
		if($stmt->execute()){
			echo "done";
		}
		
		if(stmt->fetch()){
			echo "yes";
			update($_GET["email"], $_GET["code"]);
		}else{
			echo "no";
		}
		
		function update($email, $code){
			
		$stmt=$cnx->prepare("update user set state='1' where email=? and state =?");
		$stmt = bind_param( "ss", $email, $code );
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