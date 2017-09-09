$(document).ready(function () {
   $("a#edit_icon").on("click",function () {
       $('#name', modal).val("");
       $('#station_number', modal).val("");
       $('#station_description', modal).val("");
       $('#competition', modal).val("");

        var modal = $("#editCompetitionModal");
       var stationName = $(this).closest('tr').find('td#station_name').html();
       var stationNumber = $(this).closest('tr').find('td#station_number').html();
       var stationDescription = $(this).closest('tr').find('td#station_description').html();
       var stationCompetition = $(this).closest('tr').find('td#station_competition').html();
       var stationID = $(this).closest('tr').find('td#station_id').html();

       console.log(stationName);


       $('#name', modal).val(stationName);
       $('#station_number', modal).val(stationNumber);
       $('#station_description', modal).val(stationDescription);
       $('#station_id', modal).val(stationID);
       var option = document.getElementById("competition");// $('#competition', modal).html();
       for(var i = 0;i<option.options.length;i++){
           if(option.options[i].text == stationCompetition){
               option.selectedIndex = i;
               //option.value = option[i];
               break;
           }
       }
   })
});
