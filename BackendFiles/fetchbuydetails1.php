<?php
$dbhost = 'localhost';
         $dbuser = 'shivalika';
         $dbpass = 'riya2203';
         $dbname='demodb';
         $conn = mysqli_connect('localhost', 'shivalika', 'riya2203','demodb');
         if(! $conn ) {
            die('Could not connect: ' . mysql_error());
         }
else{
echo "connected";
$response=array();
$author=$_POST['author'];
$subject=$_POST['subject'];
$semester=$_POST['semester'];
$res= "select *  from autofilldetails where semester='$semester' and subject='$subject' and bookauth = '$author' ";
$q= $conn->query($res);
if($q->num_rows >0)
{
$result=mysqli_query($conn,$res);
while($row=mysqli_fetch_array($result)){
echo "hi";
$response['bookpub']=$row['bookpub'];
$response['bookisbn']= $row['bookisbn'];
$res2 = "select * from imageupload where enrollnum = '".$row['enrollnum']."' and isbn='".$row['bookisbn']."' ";
$p=$conn->query($res2);
if($p->num_rows >0)
{
	echo "inside if";
	$result2= mysqli_query($conn,$res2);
		while($row2=mysqli_fetch_array($result)){
				echo"inside while";
				$reponse['cost']= $row2['cost'];
				$response['edition']=$row2['edition'];
			}
		}
	}
}
echo json_encode($response);
  echo $response;
mysqli_close($conn);
?>

