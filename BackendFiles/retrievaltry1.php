<?php
$conn= mysqli_connect("localhost","shivalika","riya2203","demodb");
if(!$conn){
die('could not connect:'.mysql_error());
}
$filename = "./uploads/lock.jpg";
$handle = fopen($filename, "rb");
$contents = fread($handle, filesize($filename));
fclose($handle);
header("content-type: image/jpg");
echo $contents;
?>
