/**
 * Created by keli on 2017.07.03..
 */
$(document).ready(function () {

    $("#exercise_bin_icon").click(function(){
        var exerciseId = $(this).attr("data-comp_id");
        var deletedUrl = "/exercise/delete/" + exerciseId;
        $("#deleteExercise").attr("href", deletedUrl);
    });


    $("#regForm").submit(function (event) {    // check userName and pass before submit
        validateUserNameIsExists(function (data) {
            if (data === true) {
                event.preventDefault();
                alert("A felhasználónév már használatban van");
                $("#user_name").val("");
                changeUserNameColor();
            } else {
                changeUserNameColorToDefault();
            }
        });

        var isValidPass = validateUserPassword();
        if (!isValidPass) {
            event.preventDefault();
            changePasswordColor();
        } else {
            changePasswordColorToDefault();
        }
    });

    var validateUserNameIsExists = function (callback) {
        var userName = $('#user_name').val();
        return $.ajax({
            method: "GET",
            url: "/registration/" + userName,
            async: false,
            success: callback,
            error: function () {
                alert("Something went wrong!");
            }
        });
    };

    var validateUserPassword = function () {
        var $passwordOne = $('#password').val();
        var $passwordTwo = $('#password2').val();
        var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])/;

        if ($passwordOne === $passwordTwo) {
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
    };

    var changeUserNameColor = function () {
        $("#user_name").parent(".input-group").attr("class", "input-group has-error");
    };

    var changeUserNameColorToDefault = function () {
        $("#user_name").parent(".input-group").attr("class", "input-group");
    };

    var changePasswordColor = function () {
        $("#password").parent(".input-group").attr("class", "input-group has-error");
        $("#password2").parent(".input-group").attr("class", "input-group has-error");
    };

    var changePasswordColorToDefault = function () {
        $("#password").parent(".input-group").attr("class", "input-group");
        $("#password2").parent(".input-group").attr("class", "input-group");
    };

});

