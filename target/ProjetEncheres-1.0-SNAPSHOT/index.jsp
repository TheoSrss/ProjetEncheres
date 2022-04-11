<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Accueil</title>
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
<c:if test="${success != null }">
    <div role="alert" aria-live="assertive" aria-atomic="true" class="toast" data-autohide="false">
        <div class="toast-header">
            <strong class="mr-autoS">${success}</strong>
            <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
    </div>
</c:if>
<form method="POST" action="home">
    <div class="form-group">
        ${catSelected}
        <label class="form-label" for="category">Categories :</label>
        <select id="category" name="category" class="form-control">
            <option value="null">Toutes</option>

            <c:forEach var="cat" items="${categories}">
                <option value="${cat.id}">${cat.wording}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group">
        <label class="form-label" for="name">Filtres :</label>
        <input type="text" id="name" name="name" class="form-control" placeholder="Le nom de l'article contient">
    </div>
    <input style="margin-top: 20px" type="submit" value="Rechercher" class="btn btn-primary">

</form>
<div class="containerArticles">
    <c:forEach var="a" items="${articles}">
        <div class="containerArticle card">
            <c:if test="${sessionScope.user != null }">
                <a href="article?idArticle=${a.id}" class="hrefArticle"></a>
            </c:if>
            <img class="card-img-top" src="..." alt="Card image cap">
            <div class="card-body">
                <h5 class="card-title">${a.name}</h5>
                <p class="card-text">${a.description}</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Prix : ${a.initialPrice} </li>
                <li class="list-group-item">Fin de l'enchere : ${a.dateEndBid}</li>
                <li class="list-group-item">Vendeur : <a href="profile?idUser=${a.user.id}">${a.user.username}</a>
                </li>
            </ul>

        </div>
    </c:forEach>
</div>
</body>
<script>
    let cat =${catSelected};
    let name = '${nameArticle}';

    cat !== -1 ? $('#category').val(cat) : null
    name ? $('#name').val(name) : null


</script>
</html>