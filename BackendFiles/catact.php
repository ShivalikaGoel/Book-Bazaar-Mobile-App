<?php
$connection =  mysqli_connect('localhost' , 'shivalika' , 'riya2203' , 'demodb');
$id = $_POST["id"];
$query = "select isbn , any_value(bookname),any_value(subject),any_value(bookauth),any_value(id) from imageupload natural join (select * from autofilldetails)tmp where imageupload.enrollnum=tmp.enrollnum and imageupload.isbn=tmp.bookisbn group by isbn";
$result = mysqli_query($connection,$query);
while($row = mysqli_fetch_assoc($result)){
   $array[] = $row;
}
header('Content-Type:Application/json');
echo json_encode($array);
?>
