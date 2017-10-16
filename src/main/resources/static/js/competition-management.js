$(document).ready(function () {
    $("a#edit_icon").on("click",function () {
        var modal = $("#editCompetitionModal");
        $('#name', modal).val("");
        $('#location', modal).val("");
        $('#date', modal).val("");
        $('#id', modal).val("");

        var competitionName = $(this).closest('tr').find('td#competition_name').html();
        var competitionLocation = $(this).closest('tr').find('td#competition_location').html();
        var competitionDate = $(this).closest('tr').find('td#competition_date').html();
        var competitionID = $(this).closest('tr').find('td#competition_id').html();

        $('#name', modal).val(competitionName);
        $('#location', modal).val(competitionLocation);
        $('#date', modal).val(competitionDate);
        $('#ID', modal).val(competitionID);
    })
});
