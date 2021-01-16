
<?php
$con=mysqli_connect("localhost","shivalika","riya2203","demodb");
if($con)
echo "Success";
else
echo "no conn establish";
$fcm_token=$_POST["fcm_token"];
$sql="insert into fcm_info values('$fcm_token');";
mysqli_query($con,$sql);
mysqli_close($con);
?>
