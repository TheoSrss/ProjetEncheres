<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
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
<div class="container">
    <form method="POST" action="login">
        <div class="form-group">
            <label class="form-label" for="login">Identifiant</label>
            <input type="text" name="login" id="login" class="form-control">
        </div>
        <div class="form-group">
            <label class="form-label" for="login">Identifiant</label>
            <input type="password" name="password" id="password" class="form-control">
        </div>
        <input type="submit" value="Se connecter" class="btn btn-primary">
    </form>
    <a href="registration">
        <button class="btn btn-info">Créer un compte</button>
    </a>

</div>
</body>
</html>
