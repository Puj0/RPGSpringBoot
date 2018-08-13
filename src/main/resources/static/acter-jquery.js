$(document).ready(function(){
    $("#Games").hide();
    $("#Acters").show();
});

$(document).on("click", "#tabActers", function(){
    console.log("tabActers");
    $("#Games").hide();
    $("#Acters").show();
});

$(document).on("click", "#tabGames", function(){
    console.log("tabGames");
    $("#Games").show();
    $("#Acters").hide();
});

$('#addActer').click(function(){
    console.log("Hello addActer")
    var query = $('#query').valueof();
    $.ajax({
        url: '/acters',
        type: "POST",
        data: query,
        dataType: 'application/json; charset=utf-8',
        success: function (data) {
            alert(data);
            for (var i = 0; i < data.length; i++) {
                content = data[x].id;
                content += "<br>";
                content += data[x].name;
                content += "<br>";
                $(content).appendTo("#ActersList");
            }
        }
    })
})