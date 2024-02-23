
<?php


	#--- Ajout d'utilisateur dans la bdd #

	#On inclut le fichier db_connect.php
	include ("db_connect.php");


	if(isset($_POST["email"] && isset($_POST["code"]) {
		$to=$_POST["emal"];
		$subject="Verify Code";
		$message="Click to verify : \n"."https:elo_company.com/otp/active.php?email=".$_$_POST["email"]."&code=".$_POST["code"];
		mail($to, $subject, $message);
		
		
		
		$stmt=$cnx->prepare("insert into user(email, state)" value(?,?,?,?));
		$stmt = bind_param("ss", $_POST["nom"], $_POST["age"], $_POST["email"], $_POST["code"] );
			
		if($stmt->execute()){
			echo "done";
		}
		msqli_close($con);
	}



?>