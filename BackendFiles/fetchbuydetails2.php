<?php

         $conn = mysqli_connect('localhost', 'shivalika', 'riya2203','demodb');
         if(! $conn ) {
            die('Could not connect: ' . mysql_error());
         }
$responses=array();
$response = array();
$author=$_POST['author'];
$subject=$_POST['subject'];
$semester=$_POST['semester'];
$res = "select * from imageupload natural join (select * from autofilldetails where subject='$subject' and bookauth='$author' and semester='$semester')tmp where imageupload.enrollnum=tmp.enrollnum and imageupload.isbn=tmp.bookisbn";
$q= $conn->query($res);
if($q->num_rows >0)
{
        $result= mysqli_query($conn,$res);
                while($row=mysqli_fetch_array($result)){
                               $response['edition']=$row['edition'];
$response['bookcond']=$row['bookcondition'];
$response['name']=$row['bookname'];
//$response['pub']=$row['bookpub'];
$response['cost']=$row['cost'];
$filename = $row['filepath'];
$imagedata = file_get_contents($filename);
$imagedata = file_get_contents($filename);
$strimg = base64_encode($imagedata);
//$strimg=rtrim(strtr(base64_encode($base64), '+/', '-_'), '=');
$response['picture']= $strimg;
$response['cost']=$row['cost'];
array_push($responses,$response);
                        }
                }
echo(json_encode($responses));
mysqli_close($conn);
?>

