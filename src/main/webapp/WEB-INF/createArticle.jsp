<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Création d'article</title>
</head>
<body>
<div class="container">
    <form method="POST" action="createArticle">
        <div class="form-group">
            <label class="form-label" for="name">Nom :</label>
            <input type="text" id="name" name="name" class="form-control">
        </div>
        <div class="form-group">
            <label class="form-label" for="description">Description :</label>
            <input type="text" id="description" name="description" class="form-control">
        </div>
        <div class="form-group">
            <label class="form-label" for="dateStartBid">Date de début :</label>
            <input type="datetime-local" id="dateStartBid" name="dateStartBid" class="form-control">
        </div>
        <div class="form-group">
            <label class="form-label" for="dateEndBid">Date de fin :</label>
            <input type="datetime-local" id="dateEndBid" name="dateEndBid" class="form-control">
        </div>
        <div class="form-group">
            <label class="form-label" for="initialPrice">Prix de départ :</label>
            <input type="text" id="initialPrice" name="initialPrice" class="form-control">
        </div>
        <input type="submit" value="Créer" class="btn btn-primary">
    </form>
</div>
</body>
</html>
