<?php
$conn=mysqli_connect("localhost","shivalika","riya2203","demodb");
$enroll=$_POST["enroll"];
$email=$_POST["email"];
$vkey=$_POST["vkey"];

$q=mysqli_query($conn,"select *from emailverify where enum='$enroll' OR email='$email'");
if($q->num_rows>0)
{
  while($row=mysqli_fetch_array($q))
     { 

	if($row['email']==$email && $row['enum']==$enroll && $row['verify']==1)
	echo "already registed";
	else if($row['email']==$email && $row['enum']==$enroll && $row['verify']==0)
	echo "please verify your email";
	else if($row['email']==$email && $row['verify']==1)
	echo "email already exsists" ;
	else if($row['enum']==$enroll && $row['verify']==1)
	echo "enrollment number already exsists";
	else 
	echo "error in fetch";
     }
}
else
{
$ver=0;
$insdata=mysqli_query($conn,"insert into emailverify(`enum`,`email`,`vkey`,`verify`) values ('$enroll','$email','$vkey','$ver')");
if($insdata)
echo "inserted";
else
echo "error";
}
?>

