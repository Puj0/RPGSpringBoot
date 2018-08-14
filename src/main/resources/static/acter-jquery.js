$(document).ready(function(){
    $("#Games").hide();
    $("#Acters").show();
    loadActers();
    loadGames();
});

$(document).on("click", "#tabActers", function(){
    $("#Games").hide();
    $("#Acters").show();
});

$(document).on("click", "#tabGames", function(){
    $("#Games").show();
    $("#Acters").hide();
});

$(document).on("click", "#loadActers", function(){
    loadActers();
});

function loadActers(){
    var url = "/acters?";
    var attack = $("#attackValue").val();
    var initiative = $("#initiativeValue").val();
    if (attack != 0){
        url += "attack=" + attack;
        if (initiative != 0){
            url += "&";
        }
    }
    if (initiative != 0){
        url += "initiative=" + initiative;
    }
    $.get(url, function(data){
        $("#acter-table tbody").remove();
        $("#acter-table").append("<tbody>");
        $.each(data,function(i,acter) {            
            $("#acter-table").append(
                "<tr><td>" + acter.id + "</td>" +
                "<td>" + acter.className + "</td>" +
                "<td>" + acter.name + "</td>" +
                "<td>" + acter.attack + "</td>" +
                "<td>" + acter.defence + "</td>" +
                "<td>" + acter.initiative + "</td>" +
                "<td>" + acter.roleClass + "</td>" +
                "<td>" + acter.healthPoints + "</td></tr>");
        });
        $("#acter-table").append("</tbody>");
        clearNullCells();
    });
};

$(document).on("click", "#loadGames", function(){
    loadGames();
});

function loadGames(){
    var url = "/games?";
    var rounds = $("#roundsValue").val();
    if (rounds != 0){
        url += "totalRounds=" + rounds;
    }
    $.get(url, function(data){
        $("#game-table tbody").remove();
        $("#game-table").append("<tbody>");
        $.each(data, function(i, game) {
            $("#game-table").append(
                "<tr><td>" + game.id + "</td>" +
                "<td>" + game.result + "</td>" +
                "<td>" + game.totalRounds + "</td></tr>");
        });
        $("#game-table").append("</tbody>");
    });
};

function clearNullCells(){
    $("td:contains(null)").each(function(){
        $(this).text("");
    });
};

$(document).on("click", "#createActers", function(){
    var minNum = $("#minNumOfHeroes").val();
    $.ajax({
        type: "POST",
        url: "/acters/addActers/",
        data: JSON.stringify({
            "minNumOfHeroes": minNum
        }),
        contentType:"application/json; charset=utf-8",
        success: function(data){
            $("#acter-table tbody").remove();
            $("#acter-table").append("<tbody>");
            $.each(data,function(i,acter) {            
                $("#acter-table").append(
                    "<tr><td>" + acter.id + "</td>" +
                    "<td>" + acter.className + "</td>" +
                    "<td>" + acter.name + "</td>" +
                    "<td>" + acter.attack + "</td>" +
                    "<td>" + acter.defence + "</td>" +
                    "<td>" + acter.initiative + "</td>" +
                    "<td>" + acter.roleClass + "</td>" +
                    "<td>" + acter.healthPoints + "</td></tr>");
            });
            $("#acter-table").append("</tbody>");
            clearNullCells();
        },
        dataType: "json"
      });
});

$(document).on("click", "#runGame", function(){
    var rounds = $("#roundsToRun").val();
    $.ajax({
        type: "POST",
        url: "/games/",
        data: JSON.stringify({
            "rounds": rounds
        }),
        contentType:"application/json; charset=utf-8",
        success: function(){
            loadGames();
        },
        dataType: null
      });
});

