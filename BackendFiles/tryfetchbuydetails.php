<?php
$connection =  mysqli_connect('localhost' , 'shivalika' , 'riya2203' , 'demodb');
$id = $_POST["id"];
$subject= $_POST["subject"];
$bauthor= $_POST["author"];
$query = "select id,bookcondition, cost,edition, enrollnum,filepath,bookname from imageupload natural join (select * from autofilldetails where subject='$subject' and bookauth='$bauthor')tmp where imageupload.enrollnum=tmp.enrollnum and imageupload.isbn=tmp.bookisbn";
$result = mysqli_query($connection,$query);
while($row = mysqli_fetch_assoc($result)){
   $array[] = $row;
}
header('Content-Type:Application/json');
echo json_encode($array);
?>
