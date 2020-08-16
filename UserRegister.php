<?php

require "ByteConnection.php";
$firstName = $_POST["firstName"];
$lastName = $_POST["lastName"];
$password = $_POST["password"];
$repassword = $_POST["repassword"];
$email = $_POST["email"];

if($password == $repassword){

$mysql_qry = "insert into users (userFirstName,userLastName,userEmail,userPassword) values ('$firstName','$lastName','$email','$password')";
}else{
echo "Your password is not the same and is invalid.";
}

if($con->query($mysql_qry) === TRUE)
{
echo "Account created";
}
else{
 echo "Your account has not been created, sorry for inconvenience, please try again later."

 echo "Error: " . $mysql_qry . "<br>" . $con->error;
}

$con->close();




?>