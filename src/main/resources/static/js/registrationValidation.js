/**
 * Created by keli on 2017.07.03..
 */

$(document).ready(function(){
    $("#submit_button").click(function() {
        var $passwordOne = $('#password').val();
        var $passwordTwo = $('#password2').val();
        var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])/;

        if ($passwordOne === $passwordTwo){
            if (!(reg.test($passwordOne))) {
                alert("A jelszónak tartalmaznia kell legalább 1 kis- és nagybetűt, valamint egy számot is!");
                $("#password").val("");
                $("#password2").val("");
            }
        } else {
            alert("A két jelszó nem lehet eltérő!");
            $("#password").val("");
            $("#password2").val("");
        }
    });
});

