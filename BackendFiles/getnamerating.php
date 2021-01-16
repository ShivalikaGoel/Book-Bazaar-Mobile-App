<?php
$conn= mysqli_connect("localhost","shivalika","riya2203","demodb");
if(!$conn)
{
die('error'.mysql_error());
}
$enum= $_POST["enrollnum"];
$q = "select name , rating from userdetails where enroll='$enum'";

