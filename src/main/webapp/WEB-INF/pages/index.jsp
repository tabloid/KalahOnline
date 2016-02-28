<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
<title>index</title>

<link rel="stylesheet" href="weblib/css/style.css" type="text/css"/>
<jsp:include page="headInclude.jsp"/>
<script src="weblib/js/Controller.js"></script>

</head>
<body onload="Controller.getAvailableGames(); Controller.cleanInput();Controller.createGame()">
	<div class="container">
		<div class="header">
        	<h3 class="text-muted">Kalah project</h3>
		</div>
		<div class="row">
            <div class="col-sm-9">
                <div class="panel panel-default">
                    <div class='panel-heading'>Registration form</div>
                    <div class='panel-body'>
                        <div id="form">
                            <p>New user</p>
                            <p><input type="text" name="player"></p>
                            <p id="id">Your game id </p>
                            <p><input type="button" onclick="Controller.registerPlayer()" value="Submit" class="btn btn-default"></p>
                        </div>
                    </div>
                </div>
            </div>
		    <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class='panel-heading'>Available game ids</div>
                    <div class='panel-body' id="list"></div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class='panel-heading'>Kalah board</div>
                    <div class='panel-body' id="board">
                    </div>
                </div>
            </div>
        </div>
	</div>
</body>
</html>
