<%--
  Created by IntelliJ IDEA.
  User: ThéoSOURISSEAU
  Date: 05/04/2022
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:if test="${sessionScope.user == null }">
        <form method="POST" action="registration">
            <label for="username">Pseudo :</label>
            <input type="text" id="username" name="username">
            <label for="surname">Nom :</label>
            <input type="text" id="surname" name="surname">
            <label for="firstName">Prénom :</label>
            <input type="text" id="firstName" name="firstName">
            <label for="email">Email :</label>
            <input type="text" id="email" name="email">
            <label for="phone">Téléphone :</label>
            <input type="tel" id="phone" name="phone">
            <label for="street">Rue :</label>
            <input type="text" id="street" name="street">
            <label for="postalCode">Code postal :</label>
            <input type="number" id="postalCode" name="postalCode">
            <label for="city">Ville :</label>
            <input type="text" id="city" name="city">
            <label for="password">Mot de passe :</label>
            <input type="password" id="password" name="password">
            <label for="passwordCheck">Confirmation:</label>
            <input type="password" id="passwordCheck" name="passwordCheck">
            <input type="submit" value="Créer">
            <input type="submit" value="Annuler">
        </form>
    </c:if>
    <c:if test="${sessionScope.user != null }">
        <span>Vous êtes déjà inscrit sur la platforme</span>
    </c:if>
</head>
<body>

</body>
</html>
