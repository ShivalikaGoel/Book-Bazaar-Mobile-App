
<?php
$dbhost = 'localhost';
         $dbuser = 'shivalika';
         $dbpass = 'riya2203';
         $dbname='demodb';
         $conn = mysqli_connect($dbhost, $dbuser, $dbpass,$dbname);
         if(! $conn ) {
            die('Could not connect: ' . mysql_error());
         }
$password=$_POST['password'];
$enrollnum=$_POST['enrollnum'];
$res= "select *  from signupdetails where enrollnum= $enrollnum ";
$q= $conn->query($res);
if($q->num_rows >0)
{
$qs= mysqli_query($conn,"update signupdetails  set password='$password'  WHERE enrollnum='$enrollnum' ");
if($qs == 1){
$result=mysqli_query($conn,$res);
while($row=mysqli_fetch_array($result)){
echo "hi";
echo $row['email'];
}
}
}
else
  echo $q->num_rows;
mysqli_close($conn);
?>
