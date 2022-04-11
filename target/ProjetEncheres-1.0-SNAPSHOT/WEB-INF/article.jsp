<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article </title>
</head>
<body>
<div class="container">
    <h5 style="text-align: center;margin-bottom: 15px">Article</h5>
    <ul class="list-group">
        <li class="list-group-item">${article.name}</li>
        <li class="list-group-item">Description: ${article.description}</li>
        <li class="list-group-item">Catégorie: ${article.category.wording} </li>
        <li class="list-group-item">Email: ${profileUser.email}</li>
        <li class="list-group-item">Meilleure offre: 55</li>
        <li class="list-group-item">Mise a prix: ${article.initialPrice}</li>
        <li class="list-group-item">Fin de l'enchère: ${article.dateEndBid}</li>
        <li class="list-group-item">Vendeur: ${article.user.username}</li>
    </ul>
</div>
</body>
</html>
