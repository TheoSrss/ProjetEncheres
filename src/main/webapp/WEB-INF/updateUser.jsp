<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier mon profil</title>
</head>
<body>

<form method="POST" action="updateUser">
    <label for="username" >Pseudo :</label>
    <input type="text" id="username" name="username" value="${sessionScope.user.username}">
    <label for="surname">Nom :</label>
    <input type="text" id="surname" name="surname" value="${sessionScope.user.surname}">
    <label for="firstName">Prénom :</label>
    <input type="text" id="firstName" name="firstName"  value="${sessionScope.user.firstName}">
    <label for="email">Email :</label>
    <input type="text" id="email" name="email"  value="${sessionScope.user.email}">
    <label for="phone">Téléphone :</label>
    <input type="tel" id="phone" name="phone"  value="${sessionScope.user.phone}">
    <label for="street">Rue :</label>
    <input type="text" id="street" name="street"  value="${sessionScope.user.street}">
    <label for="postalCode">Code postal :</label>
    <input type="number" id="postalCode" name="postalCode"  value="${sessionScope.user.postalCode}">
    <label for="city">Ville :</label>
    <input type="text" id="city" name="city"  value="${sessionScope.user.city}">
    <label for="password">Mot de passe :</label>
    <input type="password" id="password" name="password" value="${sessionScope.user.password}">
    <label for="passwordCheck">Confirmation:</label>
    <input type="password" id="passwordCheck" name="passwordCheck">
    <input type="submit" value="Modifier">
</form>
<c:if test="${error != null }">
    ${error}
</c:if>


</body>
</html>
