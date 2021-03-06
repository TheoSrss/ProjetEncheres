<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">--%>
    <style rel="stylesheet">
        <%@include file="style.css" %>
    </style>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>--%>

</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="home">ENI Encheres</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${sessionScope.user == null }">
                <li class="nav-item active"><a class="nav-link" href="login">S'inscrire - Se
                    connecter</a></li>
            </c:if>
            <c:if test="${sessionScope.user != null }">
                <li class="nav-item active"><a class="nav-link" href="createArticle">Vendre un article</a></li>
                <li class="nav-item active"><a class="nav-link" href="profile?idUser=${sessionScope.user.id}">Mon
                    profil</a></li>
                <li class="nav-item active"><a class="nav-link" href="disconnect">D??connexion</a></li>
            </c:if>
        </ul>
        <div class="usernameM">
            <span class="float-left">${sessionScope.user.credit}</span>
            <span  class="float-left ">${sessionScope.user.username}</span>
        </div>
    </div>
    <div class="usernameD">
        <span style="margin-left: 10px" class="float-right">Credit :${sessionScope.user.credit}</span>
        <span  class="float-right ">${sessionScope.user.username}</span>
    </div>
<%--    <span class="float-right usernameD">${sessionScope.user.username}</span>--%>
</nav>

<script>
    $(document).ready(function () {
        $('.toast').toast('show');
    });
</script>