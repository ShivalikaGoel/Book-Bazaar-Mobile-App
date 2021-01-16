<?php
$dbhost='localhost';
$dbuser='shivalika';
$dbpass='riya2203';
$dbname='demodb';
$conn = new mysqli($dbhost, $dbuser, $dbpass, $dbname);
$DefaultId = 0;

 $ImageData = $_POST['image_data'];

 $ImageName = $_POST['image_tag'];

 $GetOldIdSQL ="SELECT id FROM ImageUpload ORDER BY id ASC";

 $Query = mysqli_query($conn,$GetOldIdSQL);

 while($row = mysqli_fetch_array($Query)){

 $DefaultId = $row['id'];
 }

 $ImagePath = "images/$DefaultId.png";

 $ServerURL = "http://159.65.158.141/$ImagePath";
 $InsertSQL = "insert into ImageUpload (image_data,image_tag) values ('$ServerURL','$ImageName')";

 if(mysqli_query($conn, $InsertSQL)){

if( file_put_contents($ImagePath,base64_decode($ImageData))>0){

 echo "Your Image Has Been Uploaded.";
}
}
 mysqli_close($conn);
?>
