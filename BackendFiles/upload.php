<?php
$dbhost='localhost';
$dbuser='shivalika';
$dbpass='riya2203';
$dbname='demodb';

$upload_path ='./uploads/';
$server_ip="159.65.158.141";
$upload_url = 'http://'.$server_ip.'/UploadExample/'.$upload_path;
echo $upload_url ;
$response =array();
if($_SERVER['REQUEST_METHOD']== 'POST' )
{
	if(isset($_POST['name']) and isset($_FILES['image']['name']))
	{
		echo('inside post');
		$con =mysqli_connect("localhost","shivalika","riya2203","demodb") or die ('unable to connect to database');
	}
	else
	{
		echo('notworkingisset');
	}
	if($con)
	{
		echo('connected');
	}
	else
	{
		echo('notconncted');
	}
	$name=$_POST['name'];
$enroll=$_POST['enrollnum'];
$isbn=$_POST['isbn'];
$edition=$_POST['edition'];
$condition=$_POST['condition'];
$cost=$_POST['cost'];
	$fileinfo =pathinfo($_FILES['image']['name']);
	$extension=$fileinfo['extension'];
echo "b4 enum";
echo $enroll;
echo"after enum";
	$file_url=$upload_url . getFileName() . '.' .$extension;

	$file_path=$upload_path . getFileName() . '.' .$extension;
	try{

		move_uploaded_file($_FILES['image']['tmp_name'],$file_path);
		$sql="INSERT INTO imageupload (`enrollnum`,`isbn`,`edition`,`bookcondition`,`cost`,`filepath`) VALUES ('$enroll','$isbn','$edition','$condition','$cost','$file_path')";
		if(mysqli_query($con,$sql)){
			$response['error']=false;
			$response['url']=$file_url;
			$response['name']=$name;
			echo('insidequery');

		}
else{
echo"no sql done";
}
	}
	catch(Exception $e)
	{
		echo('queryexception');
		$response['error']=false;
		$reponse['message']=$e->getMessage();
	}

	mysqli_close($con);
}
else
{
	echo('high55');
	$response['error']=true;
	$response['message']='please choose a file';
}


echo(json_encode($response));

function getFileName(){

	echo('gotthename');
	$con =mysqli_connect("localhost","shivalika","riya2203","demodb") or die ('unable to connect to database');
	$sql="Select max(id) as id from imageupload";
	$result =mysqli_fetch_array(mysqli_query($con,$sql));
	mysqli_close($con);
	if($result['id']==null)
	{
		return 1;
	}
	else
	{
		return ++$result['id'];
	}
}
?>
