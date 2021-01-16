<?php

         $conn = mysqli_connect('localhost', 'shivalika', 'riya2203','demodb');
         if(! $conn ) {
            die('Could not connect: ' . mysql_error());
         }
$response=array();
$author=$_POST['isbn'];
$res = "select * from  autofilldetails where bookisbn='$author'";
$q= $conn->query($res);
if($q->num_rows >0)
{
        $result= mysqli_query($conn,$res);
                while($row=mysqli_fetch_array($result)){
                               $response['bookname']=$row['bookname'];
$response['bookauthor']=$row['bookauth'];
$response['subject']=$row['subject'];
$response['semester']=$row['semester'];
$response['pub']=$row['bookpub'];
$response['ans']="true";
                        }
 }
else
$response['ans']="false";
echo(json_encode($response));
mysqli_close($conn);
?>
