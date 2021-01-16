<?php
$connection =  mysqli_connect('localhost' , 'shivalika' , 'riya2203' , 'demodb');
$id = $_POST["id"];
$query = "select project_name , skill_name,project_desc,other_skills,eligible,level from recruit";
$result = mysqli_query($connection,$query);
while($row = mysqli_fetch_assoc($result)){
   $array[] = $row;
}
header('Content-Type:Application/json');
echo json_encode($array);
?>
