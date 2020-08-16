<?php
require(ByteConnection.php);

$password = $_POST["password"];
$email = $_POST["email"];

$myQry = "select * from users where userEmail like '$email' and userPassword like '$password';";

$result = mysqli_query($con,$myQry);

if(mysqli_num_rows($result) = 0)
{
echo "Incorrect login details, make sure your email and password is correct or register to create a new account.";
}
 



?>
