$(document).ready(function () {
    $(".deleteExercise").click(function () {
        var exerciseId = $(this).attr("data-comp_id");
        updateDeleteExerciseModal(exerciseId);
    });

    $(".editExercise").click(function () {
        var exerciseId = $(this).attr("data-comp_id");
        updateEditExerciseModal(exerciseId);
    });

    var updateDeleteExerciseModal = function (exerciseId) {
        var deletedUrl = "/exercise/delete/" + exerciseId;
        $("#deleteExercise").attr("href", deletedUrl);
    };

    var updateEditExerciseModal = function (exerciseId) {
        $.ajax({
            url: "/exercise/edit/" + exerciseId,
            method: "GET",
            success: function (data) {
                $("#exerciseName").val(data["name"]);
                $("#exerciseDesc").val(data["description"]);
                $("#exerciseId").val(data["id"]);
            },
            error: function () {
                alert("Something went wrong!")
            }
        })
    };

});
