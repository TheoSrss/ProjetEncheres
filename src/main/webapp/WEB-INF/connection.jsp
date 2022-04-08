<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Connexion</title>
</head>
<body>
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
    <c:if test="${error != null }">
        ${error}
    </c:if>
</div>
</body>
</html>
