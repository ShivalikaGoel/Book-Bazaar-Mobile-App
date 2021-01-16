<?php

$con=mysqli_connect("localhost","shivalika","riya2203","demodb");
if($con)
echo "Success";
else
echo "no conn establish";
$message="hey,first notification";
$title="Book Bazar";
$path_to_fcm='https://fcm.googleapis.com/fcm/send';
$server_key="AAAAnLJsEDo:APA91bGUehM7dtCqYKLmUCI51ueE9DptH2o53Yra87ZbkW4b4sQiR2TEh04sBZm8Y_56QUnO1MkPuPI4-G3uZwY0J3JUvzUud1pWdWkFTiPHkvxy_eYuZ7XD9K0HZY1rc2AVwmvytxy9";
$key = "dL1OKVGhDgM:APA91bGR3mDC55rszu1IU20VBHuMgHUD1oOjkXRGEHqk1WEnjnTn4gecqbGnYTWcJ1nVw-N23LDHxUW4u7RfWC3hb9yFc9rzmN5JmcBZZ5TCHnbzAq2vaV-etnBq4vV1cC2gP4e9oLWi";
$headers=array(
		'Authorization :key=' .$server_key,
		'Content-Type:application/json'
	      );

$fields=array('to'=>$key,
		'data'=>array('title'=>$title,'body'=>$message), notification=>array('title'=>$title,'body'=>$message));
$payload =json_encode($fields);
echo $payload;
$curl_session=curl_init();
curl_setopt($curl_session, CURLOPT_URL,$path_to_fcm);
curl_setopt($curl_session, CURLOPT_POST,true);
curl_setopt($curl_session, CURLOPT_HTTPHEADER,$headers);
curl_setopt($curl_session, CURLOPT_RETURNTRANSFER,true);
curl_setopt($curl_session, CURLOPT_SSL_VERIFYPEER,false);
curl_setopt($curl_session, CURLOPT_IPRESOLVE,CURL_IPRESOLVE_V4 );
curl_setopt($curl_session, CURLOPT_POSTFIELDS,$payload);

$result=curl_exec($curl_session);
curl_close($curl_session);
mysqli_close($con);
?>
