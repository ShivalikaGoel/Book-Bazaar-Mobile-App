<?php

$conn=mysqli_connect("localhost","shivalika","riya2203","demodb");
if(!conn)
echo "Unsuccesfull";

$name=$_POST['name'];
$enroll=$_POST['enrollnum'];
$branch=$_POST['branch'];
$email=$_POST['email'];
$phnum=$_POST['phnum'];
$pass=$_POST['pass'];

$q=mysqli_query($conn,"INSERT INTO `userdetails` (`enroll`,`name`,`branch`,`email`,`phnum`,`pass`,`rating`,`bought`,`sold`,`rent`) VALUES ('$enroll','$name','$branch','$email','$phnum','$pass','5','0','0','0')");
if($q)
echo "success";
else
echo "error";


