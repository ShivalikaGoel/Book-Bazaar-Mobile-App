
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

$name= $_POST['name'];
$password=$_POST['password'];
$enrollnum=$_POST['enrollnum'];
$phnum=$_POST['phnum'];
$email=$_POST['email'];
echo"hey ";
echo $name;
if(isset($_POST)){
echo"set";
}
else{
echo"not set";
}
foreach($_POST as $key => $value){
echo $key;
}
echo "hey";
 $q=mysqli_query($conn,"INSERT INTO `signupdetails` (`enrollnum`,`name`,`email`,`password`,`phnum`) VALUES ('$enrollnum','$name','$email','$password','$phnum')");
 if($q)
  echo "success";
 else
  echo "error";
mysqli_close($conn);

?>

</body>

</html>


