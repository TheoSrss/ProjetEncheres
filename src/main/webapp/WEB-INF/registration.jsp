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
    <title>Créerun compte</title>
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
<c:if test="${sessionScope.user != null }">
    <div role="alert" aria-live="assertive" aria-atomic="true" class="toast" data-autohide="false">
        <div class="toast-header">
            <strong class="mr-auto">Vous êtes déjà inscrit sur la platforme</strong>
            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </div>

</c:if>
<div class="container">
    <c:if test="${sessionScope.user == null }">
        <form method="POST" action="registration">
            <div class="form-group">
                <label class="form-label" for="username">Pseudo :</label>
                <input type="text" id="username" name="username" class="form-control">
            </div>
            <div class="form-group">
                <label class="form-label" for="surname">Nom :</label>
                <input class="form-control" type="text" id="surname" name="surname">
            </div>
            <div class="form-group">
                <label class="form-label" for="firstName">Prénom :</label>
                <input class="form-control" type="text" id="firstName" name="firstName">
            </div>
            <div class="form-group">
                <label class="form-label" for="email">Email :</label>
                <input class="form-control" type="text" id="email" name="email">
            </div>
            <div class="form-group">
                <label for="phone">Téléphone :</label>
                <input class="form-control"  type="tel" id="phone" name="phone">
            </div>
            <div class="form-group">
                <label for="street">Rue :</label>
                <input class="form-control"  type="text" id="street" name="street">
            </div>
            <div class="form-group">
                <label for="postalCode">Code postal :</label>
                <input class="form-control"  type="number" id="postalCode" name="postalCode">
            </div>
            <div class="form-group">
                <label for="city">Ville :</label>
                <input class="form-control"  type="text" id="city" name="city">
            </div>
            <div class="form-group">
                <label for="password">Mot de passe :</label>
                <input class="form-control"  type="password" id="password" name="password">
            </div>
            <div class="form-group">
                <label for="passwordCheck">Confirmation:</label>
                <input class="form-control"  type="password" id="passwordCheck" name="passwordCheck">
            </div>
                <input type="submit" value="Créer" class="btn btn-primary">
        </form>
    </c:if>
</div>
</body>
</html>
<script>
    $(document).ready(function () {
        $('.toast').toast('show');
    });
</script>