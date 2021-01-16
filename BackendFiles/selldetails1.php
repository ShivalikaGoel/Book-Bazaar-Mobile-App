<?php
$dbhost='localhost';
$dbuser='shivalika';
$dbpass='riya2203';
$dbname='demodb';
$conn = new mysqli($dbhost, $dbuser, $dbpass, $dbname);
if(! $conn){
die('error'.mysql_error());
}
 $base=$_REQUEST['image'];
	// Get file name posted from Android App
	$filename = "abc";
	// Decode Image
    $binary=base64_decode($base);
echo $binary;
    header('Content-Type: bitmap; charset=utf-8');
	// Images will be saved under 'www/imgupload/uplodedimages' folder
    $file = fopen('./uploadedimages/'.$filename, 'wb');
	// Create File
    fwrite($file, $binary);
    fclose($file);
    echo 'Image upload complete, Please check your php file directory';
?>
