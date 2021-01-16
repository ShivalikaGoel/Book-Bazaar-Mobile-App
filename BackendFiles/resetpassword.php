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
$enum=$_POST['enrollment'];
$q=mysqli_query($conn,"
 if($q)
  echo "success";
 else
  echo "error";
mysqli_close($conn);
?>
