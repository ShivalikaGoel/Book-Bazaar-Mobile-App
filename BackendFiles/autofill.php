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
$enroll=$_POST['enrollnum'];
$isbn=$_POST['bookisbn'];
$title=$_POST['bookname'];
$author=$_POST['bookauth'];
$pub=$_POST['bookpub'];
$sub=$_POST['subject'];
$sem=$_POST['semester'];
echo"hey ";
echo $enroll;
echo $isbn;
echo $title;
echo $author;
echo $pub;
echo $sub;
echo $sem;
echo gettype($enroll);
if(isset($_POST)){
echo"set";
}
else{
echo"not set";
}
 $q=mysqli_query($conn,"INSERT INTO `autofilldetails` (`enrollnum`,`bookisbn`,`bookname`,`bookauth`,`bookpub`,`subject`,`semester`) VALUES ('$enroll','$isbn','$title','$author','$pub','$sub','$sem')");
 if($q)
  echo "success";
 else
  echo "error";
mysqli_close($conn);

?>

