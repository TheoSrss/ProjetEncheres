<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<nav>
    <ol>
        <c:if test="${sessionScope.user == null }">
            <li><a href="login">S'inscire - Se connecter</a></li>
        </c:if>
        <c:if test="${sessionScope.user != null }">
            <li><a>Encheres</a></li>
            <li><a>Vendre un article</a></li>
            <li><a href="profile?idUser=${sessionScope.user.id}">Mon profil</a></li>
            <li><a href="disconnect">Déconnexion</a></li>
        </c:if>

    </ol>
    </ol>
</nav>