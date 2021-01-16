<html>

<body>
<?php
$dbhost = 'localhost';
         $dbuser = 'shivalika';
         $dbpass = 'riya2203';
         $dbname='demodb';
         $conn = mysqli_connect($dbhost, $dbuser, $dbpass,$dbname);
         if(! $conn ) {
            die('Could not connect: ' . mysql_error());
         }
         echo 'Connected successfull';

 $name=$_POST['name'];
$password=$_POST['password'];
 $q=mysqli_query($conn,"INSERT INTO `login` (`name`,`password`) VALUES('$name','$password')");
 if($q)
  echo "success";
 else
  echo "error";
mysql_close($conn);

?>
</body>
</html>
