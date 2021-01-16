<html>
<body>
<?php
echo"hey";
$recieved=file_get_contents('php://input');
echo file_get_contents('php://input');
$name=time();
$filetoWrite="./uploads/-".time().".jpg";;
file_put_contents($filetoWrite,$recieved);
?>
</body>
</html>
