$(document).ready(function () {

    $(".editStation").click(function () {
        var stationId = $(this).attr("data-station_id");
        updateEditStationModal(stationId);
    });

    $(".deleteStation").click(function () {
        var stationId = $(this).attr("data-station_id");
        updateDeleteStationModal(stationId);
    });

    var updateDeleteStationModal = function (stationId) {
        var deletedUrl = "/station/delete/" + stationId;
        $("#deleteStation").attr("href", deletedUrl);
    };

    var updateEditStationModal = function (stationId) {
        $.ajax({
            url: "/station/edit/" + stationId,
            method: "GET",
            success: function (data) {
                $("#edit_station_id").val(data["id"]);
                $("#edit_station_name").val(data["name"]);
                $("#edit_station_number").val(data["number"]);
                $("#edit_station_description").val(data["description"]);
                // competition!
            },
            error: function () {
                alert("Something went wrong!")
            }
        })
    };

    // Adi's
    $("a#edit_icon").on("click", function () {
        var modal = $("#editStationModal");
        $('#name', modal).val("");
        $('#station_number', modal).val("");
        $('#station_description', modal).val("");
        $('#competition', modal).val("");

        var stationName = $(this).closest('tr').find('td#station_name').html();
        var stationNumber = $(this).closest('tr').find('td#station_number').html();
        var stationDescription = $(this).closest('tr').find('td#station_description').html();
        var stationCompetition = $(this).closest('tr').find('td#station_competition').html();
        var stationID = $(this).closest('tr').find('td#station_id').html();

        $('#name', modal).val(stationName);
        $('#station_number', modal).val(stationNumber);
        $('#station_description', modal).val(stationDescription);
        $('#station_id', modal).val(stationID);
        var option = document.getElementById("competition");// $('#competition', modal).html();
        for (var i = 0; i < option.options.length; i++) {
            if (option.options[i].text == stationCompetition) {
                option.selectedIndex = i;
                //option.value = option[i];
                break;
            }
        }
    })
});
