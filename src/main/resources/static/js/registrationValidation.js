/**
 * Created by keli on 2017.07.03..
 */
$(document).ready(function(){
    $("#regForm").submit(function(event) {    // check userName and pass before submit
        // https://api.jquery.com/submit/
        var isValidUser = validateUserNameIsExists();
        var isValidPass = validatePassword();
        if (!isValidPass || !isValidUser){
            event.preventDefault();
            if (!isValidUser) {
                console.log("user");
                changeUserNameColor();
            } else if (!isValidPass) {
                console.log("pass");
                changePasswordColor();
            }
        } else {
            $('#regForm').submit();
            alert("else ág");
            // return;
        }

    });

    var changeUserNameColor = function (){
        $("#user_name").parent(".input-group").attr("class", "input-group has-error");
    };

    var changePasswordColor = function(){
        $("#password").parent(".input-group").attr("class", "input-group has-error");
        $("#password2").parent(".input-group").attr("class", "input-group has-error");
    };

    var validateUserNameIsExists = function() {
        var userName = $('#user_name').val();
        $.ajax({
            method: "GET",
            url: "/registration/" + userName,
            success: function(data){
                if (data === "true"){
                    alert("A felhasználónév már használatban van");
                    $("#user_name").val("");
                    return false;
                } else {
                    alert("A user átment" + data);
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

