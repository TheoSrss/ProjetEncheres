<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier mon profil</title>
</head>
<body>
<c:if test="${error != null }">
    <div role="alert" aria-live="assertive" aria-atomic="true" class="toast" data-autohide="false">
        <div class="toast-header">
            <strong class="mr-auto">${error}</strong>
            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </div>
</c:if>
<form method="POST" action="updateUser">
    <div class="form-group">
        <label class="form-label" for="username">Pseudo :</label>
        <input class="form-control" type="text" id="username" name="username" value="${sessionScope.user.username}">
    </div>
    <div class="form-group">
        <label class="form-label" for="surname">Nom :</label>
        <input class="form-control" type="text" id="surname" name="surname" value="${sessionScope.user.surname}">
    </div>
    <div class="form-group">
        <label class="form-label" for="firstName">Prénom :</label>
        <input class="form-control" type="text" id="firstName" name="firstName" value="${sessionScope.user.firstName}">
    </div>
    <div class="form-group">
        <label class="form-label" for="email">Email :</label>
        <input class="form-control" type="text" id="email" name="email" value="${sessionScope.user.email}">
    </div>
    <div class="form-group">
        <label class="form-label" for="phone">Téléphone :</label>
        <input class="form-control" type="tel" id="phone" name="phone" value="${sessionScope.user.phone}">
    </div>
    <div class="form-group">
        <label class="form-label" for="street">Rue :</label>
        <input class="form-control" type="text" id="street" name="street" value="${sessionScope.user.street}">
    </div>
    <div class="form-group">
        <label class="form-label" for="postalCode">Code postal :</label>
        <input class="form-control" type="number" id="postalCode" name="postalCode"
               value="${sessionScope.user.postalCode}">
    </div>
    <div class="form-group">
        <label class="form-label" for="city">Ville :</label>
        <input class="form-control" type="text" id="city" name="city" value="${sessionScope.user.city}">
    </div>
    <div class="form-group">
        <label class="form-label" for="password">Mot de passe :</label>
        <input class="form-control" type="password" id="password" name="password" value="${sessionScope.user.password}">
    </div>
    <div class="form-group">
        <label class="form-label" for="passwordCheck">Confirmation:</label>
        <input class="form-control" type="password" id="passwordCheck" name="passwordCheck">
    </div>
    <input type="submit" value="Modifier" class="btn btn-primary">
</form>
</body>
</html>
<script>
    $(document).ready(function () {
        $('.toast').toast('show');
    });
</script>
