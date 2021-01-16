<?php
$dbhost = 'localhost';
         $dbuser = 'shivalika';
         $dbpass = 'riya2203';
         $dbname='demodb';
         $conn = mysqli_connect("localhost", "shivalika", "riya2203","demodb");
         if(! $conn ) {
            die('Could not connect: ' . mysql_error());
         }
         echo 'Connected successfull';

$proj_name=$_POST["teamName"];
$project_desc=$_POST['OneLiner'];
$skills=$_POST['MajorReq'];
$level=$_POST['Level'];
$others=$_POST['OtherSkill'];
$eligible=$_POST['WhoApply'];
echo"hey ";
if(isset($_POST)){
echo"set";
}
else{
echo"not set";
}
 $q=mysqli_query($conn,"INSERT INTO `recruit` (`project_name`,`skill_name`,`project_desc`,`other_skills`,`eligible`,`level`) VALUES ('$proj_name','$skills','$project_desc','$others','$eligible','$level')");
 if($q)
  echo "success";
 else
  echo "error";
mysqli_close($conn);
?>
