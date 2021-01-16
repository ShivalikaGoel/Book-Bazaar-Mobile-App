<?php

$dbhost = 'localhost';
         $dbuser = 'lockmanager';
         $dbpass = 'riya2203';
         $dbname='mylock';
         $conn = mysqli_connect($dbhost, $dbuser, $dbpass,$dbname);
         if(! $conn ) {
            die('Could not connect: ' . mysql_error());
         }
$str=$_POST['password'];
$sql = "SELECT keyname FROM lockkey where keyname='$str' AND  `keystatus`='not sold'";
$result = $conn->query($sql);
$sqla = "SELECT keyname FROM lockkey where keyname='$str' AND  `keystatus`='sold'";
$res= $conn->query($sqla);
$flag=0;
if($result->num_rows > 0)
{
        echo("false");
}
else if($res->num_rows > 0)
{
	echo("sold");
}
else{
echo("false");
}

?>

