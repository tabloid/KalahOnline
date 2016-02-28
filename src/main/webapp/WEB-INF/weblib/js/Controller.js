var Controller = new Object();

Controller.createGameUrl = "api/";
Controller.gameId;
Controller.player;

Controller.cleanInput = function(){
    $("input[type='text']").val("");
}
Controller.inputIsValid = function(){
    var name = $("input[type='text']");
    if (name.val() == ""){
        alert("Enter player name")
        return false;
        }
    return true;
}
Controller.getInput = function(){
    return $("input[type='text']").val();
}
Controller.getAvailableGames = function(){
    $.get(Controller.createGameUrl, function(object) {
        var html = "";
        $.each(object, function(){
            html += "<p>" +
            "<a href='#' onclick='Controller.registerPlayer(" + this + ")'>" +
                this + "</a>" + "</p>";
            });
            $("#list").html(html);
    });
}
Controller.createGame = function(){
    $.post(Controller.createGameUrl, function( data ) {
        Controller.gameId = data;
        $("#id").append(data);
    });
}
Controller.hideAvailableGames = function(){
    $('input[type="button"]').css('visibility', 'hidden');
    $("#list").html("");
}
Controller.registerPlayer = function(id){
    if (Controller.inputIsValid()){
        Controller.player = Controller.getInput();
        if (id !== undefined)
            Controller.gameId = id;
        var url = Controller.createGameUrl + Controller.gameId + "/" + Controller.player;
        $.post(url, function(status) {
            Controller.hideAvailableGames();
            if(status)
                Controller.printBoardLayout();
            else{
                alert("Waiting for opponent");
                var timer = setInterval(function(){
                    $.post(url, function(status2) {
                        if (status2){
                            Controller.printBoardLayout();
                            clearInterval(timer);
                        }
                    });
                }, 5000);
            }
        })
        .fail(function(data) {
            alert(data.responseText);
        });
    }
}
Controller.printBoardLayout = function(){
    var url = Controller.createGameUrl + Controller.gameId + "/" + Controller.player;
    $.get(url, function(object) {
        var board = $("#board");
        board.css("visibility", "visible ");
        var html = "<p>" + object.opponent + "</p>" +
        "<table class='table'>" +
        "<tr>";
        var reversedOpponentList = object.opponentBoard.pitsList.reverse();
        $.each(reversedOpponentList, function(){
            html += "<td>" + this + "</td>"
        });
        html += "</tr>" +
        "<tr>" +
        "<td>" + object.opponentBoard.kalah + "</td>" +
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td>" + object.playerBoard.kalah + "</td>" +
        "</tr>" +
        "<tr>";
        var i = 0;
        $.each(object.playerBoard.pitsList, function(){
            html += "<td>" +
            "<a href='#' onclick='Controller.makeMove(" + i + ")'>" +
            this + "</a>" + "</td>";
            i++;
        });
        html += "</tr>" + "</table>" +
        "<p>" + object.player + "</p>";
        $("#board").html(html);
    });
}
Controller.printUnClickableBoardLayout = function(){
    var url = Controller.createGameUrl + Controller.gameId + "/" + Controller.player;
    $.get(url, function(object) {
        var board = $("#board");
        board.css("visibility", "visible ");
        var html = "<p>" + object.opponent + "</p>" +
        "<table class='table'>" +
        "<tr>";
        var reversedOpponentList = object.opponentBoard.pitsList.reverse();
        $.each(reversedOpponentList, function(){
            html += "<td>" + this + "</td>"
        });
        html += "</tr>" +
        "<tr>" +
        "<td>" + object.opponentBoard.kalah + "</td>" +
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td></td>" +
        "<td>" + object.playerBoard.kalah + "</td>" +
        "</tr>" +
        "<tr>";
        $.each(object.playerBoard.pitsList, function(){
            html += "<td>" + this + "</td>"
        });
        html += "</tr>" + "</table>" +
        "<p>" + object.player + "</p>";
        $("#board").html(html);
    });
}
Controller.makeMove = function(pit){
    var url = Controller.createGameUrl + Controller.gameId + "/" + Controller.player + "/" + pit;
    $.post(url, function(flag) {
        if (flag)
            Controller.printBoardLayout();
        else
            Controller.printUnClickableBoardLayout();
        var timer = setInterval(function(){
            var url = Controller.createGameUrl + Controller.gameId + "/" + Controller.player;
            $.get(url, function(object) {
                if (object.whoseMove == Controller.player){
                    Controller.printBoardLayout();
                    clearInterval(timer);
                }
            });
        }, 5000);
    })
    .fail(function(data) {
        alert(data.responseText);
    });
}
