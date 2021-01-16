<?php
$conn=mysqli_connect("localhost","shivalika","riya2203","demodb");
if($conn)
echo "yes";
$pass=$_POST['pass'];
$enrollnum=$_POST['enrollnum'];
echo $pass;
echo $enrollnum;
$q=mysqli_query($conn,"select name from  userdetails where enroll='$enrollnum'and pass='$pass' ");
if($q)
echo "success";
else
echo "error";
if($q->num_rows>0)
{
echo "found";
}
else
echo "incorrecrt";

?>
