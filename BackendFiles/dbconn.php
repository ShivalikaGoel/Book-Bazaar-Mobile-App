<?php
$dbhost = 'localhost';
         $dbuser = 'lockmanager';
         $dbpass = 'riya2203';
         $dbname='mylock';
         $conn = mysqli_connect($dbhost, $dbuser, $dbpass,$dbname);
         if(! $conn ) {
            die('Could not connect: ' . mysql_error());
         }
         echo 'Connected successfull';


?>
