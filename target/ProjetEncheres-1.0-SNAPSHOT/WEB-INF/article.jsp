<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article </title>
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
<div class="container">
    <h5 style="text-align: center;margin-bottom: 15px">Detail de la vente</h5>
    <ul class="list-group">
        <li class="list-group-item">${article.name}</li>
        <li class="list-group-item">Description: ${article.description}</li>
        <li class="list-group-item">Catégorie: ${article.category.wording} </li>
        <c:if test="${bid != null }">
            <li class="list-group-item">Meilleure offre: ${bid.amount} par ${bid.user.username}</li>
        </c:if>
        <c:if test="${bid == null }">
            <li class="list-group-item">Aucune offre pour cet article</li>
        </c:if>
        <li class="list-group-item">Mise a prix: ${article.initialPrice}</li>
        <li class="list-group-item">Fin de l'enchère: ${article.dateEndBid}</li>
        <li class="list-group-item">Retrait: ${article.withdrawal.city}</li>
        <li class="list-group-item">Vendeur: ${article.user.username}</li>

        <c:if test="${sessionScope.user != null }">
            <c:if test="${article.user.id ne sessionScope.user.id }">
                <form method="POST" action="article?idArticle=${article.id}">
                    <div class="form-group">
                        <label class="form-label" for="price">Votre proposition :</label>
                        <input min="${min}" type="number" id="price" name="price" class="form-control">
                    </div>
                    <input style="margin-top: 20px" type="submit" value="Enchérir" class="btn btn-primary">
                </form>
            </c:if>
        </c:if>
    </ul>
</div>
</body>
</html>
