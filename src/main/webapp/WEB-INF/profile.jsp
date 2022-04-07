<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profil</title>
</head>
<body>

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
        <span>${profileUser.street}</span>
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
<span>Profil utilisateur</span>
</body>
</html>
