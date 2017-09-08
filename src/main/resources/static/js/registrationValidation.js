/**
 * Created by keli on 2017.07.03..
 */
$(document).ready(function(){
    $("#regForm").submit(function(evt) {    // check userName and pass before submit
        var isValidUser = validateUserNameIsExists();
        var isValidPass = validatePassword();
        if (!isValidPass || !isValidUser){
            evt.preventDefault();
        }
    });

    var validateUserNameIsExists = function() {
        var userName = $('#user_name').val();
        $.ajax({
            method: "GET",
            url: "/registration/" + userName,
            success: function(data){
                if (data === "true"){
                    alert("A felhasználónév már használatban van");
                    $("#user_name").val("");
                    return true;
                }
            },
            error: function(data){
                alert("Something went wrong!");
            }
        })
    };

    var validatePassword = function(){
        var $passwordOne = $('#password').val();
        var $passwordTwo = $('#password2').val();
        var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])/;

        if ($passwordOne === $passwordTwo){
            if (!(reg.test($passwordOne))) {
                alert("A jelszónak tartalmaznia kell legalább 1 kis- és nagybetűt, valamint egy számot is!");
                $("#password").val("");
                $("#password2").val("");
                return false;
            }
        } else {
            alert("A két jelszó nem lehet eltérő!");
            $("#password").val("");
            $("#password2").val("");
            return false;
        }
        return true;
    }
});

