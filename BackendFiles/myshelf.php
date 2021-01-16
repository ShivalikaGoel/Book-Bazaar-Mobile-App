<?php
$connection =  mysqli_connect('localhost' , 'shivalika' , 'riya2203' , 'demodb');
$id = $_POST["enrollnum"];
$query = "select bookname,id from imageupload natural join (select * from autofilldetails)tmp where imageupload.enrollnum='$id' and imageupload.isbn=tmp.bookisbn";

$result = mysqli_query($connection,$query);
while($row = mysqli_fetch_assoc($result)){
   $array[] = $row;
}
header('Content-Type:Application/json');
echo json_encode($array);
?>


