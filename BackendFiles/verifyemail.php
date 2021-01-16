
<?php
require './PHPMailerAutoload.php';
include_once('class.phpmailer.php');

require_once('class.smtp.php');


try{
$mail = new PHPMailer(true);
$email= $_POST['email'];
$password= $_POST['password'];
$mailid= utf8_decode(urldecode($email));
echo $mailid;
$mail->SMTPDebug = 0;
$mail->DebugOutput = 'html';
ini_set('display_startup_errors',1);
ini_set('display_errors',1);
error_reporting(E_ALL);
$mail->IsSMTP();                                      // Set mailer to use SMTP
$mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
$mail->SMTPAuth = true;                               // Enable SMTP authentication
$mail->SMTPKeepAlive = true;   
$mail->Username = 'weconnect9876@gmail.com';                 // SMTP username
$mail->Password = 'Weconnect@12';                           // SMTP password
$mail->SMTPSecure = 'ssl';                            // Enable TLS encryption, `ssl` also accepted
$mail->Port = 465;
$mail->setFrom('weconnect9876@gmail.com', 'Mailer');
$mail->addAddress('agarwal.riya04@gmail.com');     // Add a recipient               // Name is optional
$mail->addReplyTo('info@example.com', 'Information');
$mail->addCC($mailid);

// $mail->addAttachment('/var/tmp/file.tar.gz');         // Add attachments
// $mail->addAttachment('/tmp/image.jpg', 'new.jpg');    // Optional name
$mail->isHTML(true);                                  // Set email format to HTML

$mail->Subject = 'Email Verification';
$mail->Body    = "Please enter this key $password";
$mail->AltBody = 'This is the body in plain text for non-HTML mail clients';

if(!$mail->send()) {
    echo 'Message could not be sent.';
    echo 'Mailer Error: ' . $mail->ErrorInfo;
} else {
    echo 'Mail sent!!';
echo $mail->ErrorInfo;

}
}
catch(Exception $e){
echo $e->errorMessage();
}
?>
