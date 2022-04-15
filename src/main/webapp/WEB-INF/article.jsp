<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <li class="list-group-item"><img src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxISEhUSEhIVFRUVFRUVFRUVFRUVFRUVFRUXFhUVFRUYHSggGBolHRUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDQ0NFQ8PFSsZFRkrLSstLSsrLS0tKy0rKystKystKzcrNy0tNzc3Ky03Nzc3KystKy0rKysrKysrKysrK//AABEIAMIBBAMBIgACEQEDEQH/xAAYAAEBAQEBAAAAAAAAAAAAAAAAAQIDB//EABgQAQEBAQEAAAAAAAAAAAAAAAABEQIx/8QAFwEBAQEBAAAAAAAAAAAAAAAAAAECA//EABcRAQEBAQAAAAAAAAAAAAAAAAABMRH/2gAMAwEAAhEDEQA/APHEVGnQClQAFRKAAggKloAhSggACAYoAAAYCKgIKgopBAWCQqgqFoAII7iGsOiotQQDQDUABGkBKUKCFKAIqCACgAAAAgoIqFVAAFRSqIUAEVFTjqRFlc2wNJQVBNBRC0FTAAQABNAABAEBRKKKgaAAIAQABQAAENAFRR0E0YaaqGgKmiAoICpoAaIAAlEUQ0FQAAFAEEUAA0AAFAEoFVDQVEtAdECMqtoigaaAAQA1AAKiggABoACKCAKgAAAAaEEVFQUAUANETQAbEGWlABRAAEBUAAAEAAAEAAAFAEBRFAIAAAgBigCABgDYgiqAiotNAAQDAAQUEEAAAAEUVBQBFBFNAAAAFQAABAWQQFaAZFEUUEAVFTAVAUAERAAAQFQVQQAUEBQQFAAoCogpQEqoAADQCKKigAgoKCIAKBURABQABFABFAAAAAAgqAAAAIKlBdQBWgVAARRFFEFAQUBEUBBURBFFBFAAARQAAAAgAAAQVEFASgA0oI0ECQAAQAFRQBAAEVKIAAAAAAACAAoAqAAAAAAIGijQpjKgAAICggKgACAKgAAABgACAoAhoigAKAQAAAAUKADU8IDLQAqUKDKpCgqBAWaACKgAhBACgCKgCqAoHKCBQCixYChUQVFpAFh0Aiv/2Q==" alt="img"></li>
        <li class="list-group-item">Description: ${article.description}</li>
        <li class="list-group-item">Catégorie: ${article.category.wording} </li>
        <c:if test="${bid != null }">
            <li class="list-group-item">Meilleure offre: ${bid.amount} par ${bid.user.username}</li>
        </c:if>
        <c:if test="${bid == null }">
            <li class="list-group-item">Aucune offre pour cet article</li>
        </c:if>
        <li class="list-group-item">Mise a prix: ${article.initialPrice}</li>
        <li class="list-group-item">Début de l'enchère: ${article.dateStartBid}</li>
        <li class="list-group-item">Fin de l'enchère: ${article.dateEndBid}</li>
        <li class="list-group-item">Retrait: ${article.withdrawal.city}</li>
        <c:if test="${article.stateSale =='IS_WIN' }">
            <li class="list-group-item">Gagnant : <a
                    href="profile?idUser=${article.user.id}">${article.user.username}</a>
            <li class="list-group-item">Vendeur : <a
                    href="profile?idUser=${article.lastUser.id}">${article.lastUser.username}</a>
            </li>
        </c:if>
        <c:if test="${article.stateSale =='TO_SALE' }">
            <li class="list-group-item">Vendeur : <a
                    href="profile?idUser=${article.user.id}">${article.user.username}</a>
            </li>
        </c:if>

        <c:if test="${sessionScope.user != null }">
            <c:if test="${article.user.id ne sessionScope.user.id }">
                <c:if test="${article.stateSale =='TO_SALE' }">
                    <form method="POST" action="article?idArticle=${article.id}">
                        <div class="form-group">
                            <label class="form-label" for="price">Votre proposition :</label>
                            <input min="${min}" type="number" id="price" name="price" class="form-control">
                        </div>
                        <input style="margin-top: 20px" type="submit" value="Enchérir" class="btn btn-primary">
                    </form>
                </c:if>
            </c:if>
            <c:if test="${article.user.id eq sessionScope.user.id }">
                <c:if test="${canUpdateArticle}">
                    <div style="width: 100%;margin-top: 25px;display: flex;justify-content: space-evenly;align-items: center;flex-direction: row;flex-wrap: wrap;">
                        <a href="updateArticle?idArticle=${article.id}">
                            <button class="btn btn-primary"> Modifier l'article</button>
                        </a>
                        <a href="deleteArticle?idArticle=${article.id}">
                            <button class="btn btn-danger"> Supprimer l'article</button>
                        </a>
                    </div>
                </c:if>
            </c:if>
        </c:if>
    </ul>
</div>
</body>
<script>
    $('.dateEnd').each(function () {
        text = $(this).text().split('T')[0]
        console.log(text)
        $(this).text(text)
    })
</script>
</html>
