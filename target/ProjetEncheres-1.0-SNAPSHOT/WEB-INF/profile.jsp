<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profil</title>
</head>
<body>
<span>Profil utilisateur</span>

<div>
    <div>
        <span>Pseudo</span>
        <span>${profileUser.username}</span>
    </div>
    <div>
        <span>Nom</span>
        <span>${profileUser.surname}</span>
    </div>
    <div>
        <span>Prénom</span>
        <span>${profileUser.firstName}</span>
    </div>
    <div>
        <span>Email</span>
        <span>${profileUser.email}</span>
    </div>
    <div>
        <span>Téléphone</span>
        <span>${profileUser.phone}</span>
    </div>
    <div>
        <span>Rue</span>
        <span>${profileUser.street}</span>s
    </div>
    <div>
        <span>Code postal</span>
        <span>${profileUser.postalCode}</span>
    </div>
    <div>
        <span>Ville</span>
        <span>${profileUser.city}</span>
    </div>
</div>
${sessionScope.user.id}
${profileUser.id}
<c:if test="${sessionScope.user != null }">
    oui
    <c:if test="${profileUser.id eq sessionScope.user.id }">
        <a href="editProfile">
            <button>Modifier mon compte</button>
        </a>
        <a href="deleteProfile">
            <button>Supprimer mon compte</button>
        </a>
    </c:if>
</c:if>
</body>
</html>
