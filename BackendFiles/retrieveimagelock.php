<?php

         $conn = mysqli_connect('localhost', 'shivalika', 'riya2203','demodb');
         if(! $conn ) {
            die('Could not connect: ' . mysql_error());
         }
$response=array();
$imagedata = file_get_contents("./uploads/lock.jpg");
$strimg = base64_encode($imagedata);
$response["picture"]= $strimg;
echo(json_encode($response));
mysqli_close($conn);
?>

