<?php
$conn=mysqli_connect("localhost","shivalika","riya2203","demodb");
if($conn)
echo "yes";
$phnum=$_POST['phnum'];
$branch=$_POST['branch'];
$enrollnum=$_POST['enrollnum'];
echo $phnum;
echo $branch;
echo $enrollnum;
$q=mysqli_query($conn,"update userdetails set branch='$branch',phnum='$phnum' where enroll='$enrollnum' ");
if($q)
echo "success";
else
echo "error";
?>
