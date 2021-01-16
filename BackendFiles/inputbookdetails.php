<?php
$dbhost = 'localhost';
         $dbuser = 'shivalika';
         $dbpass = 'riya2203';
         $dbname='demodb';
         $conn = mysqli_connect("localhost", "shivalika", "riya2203","demodb");
         if(! $conn ) {
            die('Could not connect: ' . mysql_error());
         }
         echo 'Connected successfull';
$enroll=$_POST['enrollnum'];
$isbn=$_POST['bookisbn'];
$edition=$_POST['edition'];
$condition=$_POST['condition'];
$cost=$_POST['cost'];
$path = $_POST['path'];
echo"hey ";
echo $enroll;
echo gettype($enroll);
if(isset($_POST)){
echo"set";
}
else{
echo"not set";
}
 $q=mysqli_query($conn,"INSERT INTO `inputbookdetails` (`enrollnum`,`bookisbn`,`edition`,`condition`,`cost`,`path`) VALUES ('$enroll','$isbn','$edition','$condition','$cost','$path')");
 if($q)
  echo "success";
 else
  echo "error";
mysqli_close($conn);

?>

