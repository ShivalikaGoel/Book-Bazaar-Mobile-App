<?php
$conn=mysqli_connect("localhost","shivalika","riya2203","demodb");
$enrollnum=$_POST['enrollnum'];
$response=array();
$q=mysqli_query($conn,"select * from userdetails where enroll='$enrollnum'");
if($q->num_rows>0)
{
	$row=mysqli_fetch_array($q);
	$response['name']=$row['name'];
	$response['enroll']=$row['enroll'];
	$response['phnum']=$row['phnum'];
	$response['email']=$row['email'];
	$response['branch']=$row['branch'];
	$response['bought']=$row['bought'];
	$response['sold']=$row['sold'];
	$response['rent']=$row['rent'];
	$response['rating']=$row['rating'];
}
echo json_encode($response);
?>

