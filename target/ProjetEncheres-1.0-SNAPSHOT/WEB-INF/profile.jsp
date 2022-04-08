<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profil</title>
</head>
<body>
<div class="container">
    <h5 style="text-align: center;margin-bottom: 15px">Profil utilisateur</h5>
    <ul class="list-group">
        <li class="list-group-item">Pseudo: ${profileUser.username}</li>
        <li class="list-group-item">Nom: ${profileUser.surname}</li>
        <li class="list-group-item">Prénom: ${profileUser.firstName} </li>
        <li class="list-group-item">Email: ${profileUser.email}</li>
        <li class="list-group-item">Téléphone: ${profileUser.phone}</li>
        <li class="list-group-item">Rue: ${profileUser.street}</li>
        <li class="list-group-item">Code postal: ${profileUser.postalCode}</li>
        <li class="list-group-item">Ville: ${profileUser.city}</li>
    </ul>
    <c:if test="${sessionScope.user != null }">
        <c:if test="${profileUser.id eq sessionScope.user.id }">
            <div class="footerButtons">
            <a href="updateUser">
                <button class="btn btn-primary">Modifier mon compte</button>
            </a>
            <a href="deleteUser">
                <button  class="btn btn-danger">Supprimer mon compte</button>
            </a>

            </div>
        </c:if>
    </c:if>
</div>
</body>
</html>
