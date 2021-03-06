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
<form method="POST" action="home" class="row">
    <div class="form-group" style="left: auto;transform: translate(0,0)">
        <label class="form-label" for="category">Categories :</label>
        <select id="category" name="category" class="form-control">
            <option value="null">Toutes</option>
            <c:forEach var="cat" items="${categories}">
                <option value="${cat.id}">${cat.wording}</option>
            </c:forEach>
        </select>
    </div>
    <div class="form-group" style="left: auto;transform: translate(0,0)">
        <label class="form-label" for="name">Filtres :</label>
        <input type="text" id="name" name="name" class="form-control" placeholder="Le nom de l'article contient">
    </div>
    <c:if test="${sessionScope.user != null }">
        <div class="form-group" style="left: auto;transform: translate(0,0)">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="typeBid" value="typeBidPurchase"
                       id="typeBidPurchase">
                <label class="form-check-label" for="typeBidPurchase">
                    Achats
                </label>
            </div>
            <div class="containerCheckbox" id="checkboxsPurchase">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="bidOpen" name="bidOpen" disabled>
                    <label class="form-check-label" for="bidOpen">
                        Ench??res ouvertes
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="myBids" name="myBids" disabled>
                    <label class="form-check-label" for="myBids">
                        Mes ench??res
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="myWinBids" name="myWinBids"
                           disabled>
                    <label class="form-check-label" for="myWinBids">
                        Mes ench??res remport??es
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group" style="left: auto;transform: translate(0,0)">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="typeBid" value="typeBidMySell" id="typeBidMySell">
                <label class="form-check-label" for="typeBidMySell">
                    Mes ventes
                </label>
            </div>
            <div class="containerCheckbox" id="checkboxsMySell">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="mySellOpen" name="mySellOpen"
                           disabled>
                    <label class="form-check-label" for="mySellOpen">
                        Mes ventes en cours
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="mySellNotStart"
                           name="mySellNotStart" disabled>
                    <label class="form-check-label" for="mySellNotStart">
                        Ventes non d??but??es
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="mySellFinish"
                           name="mySellFinish" disabled>
                    <label class="form-check-label" for="mySellFinish">
                        Ventes termin??es
                    </label>
                </div>
            </div>
        </div>

    </c:if>
    <div style="left: auto;transform: translate(0,0)">
        <input style="margin-top: 20px" type="submit" value="Rechercher" class="btn btn-primary">
    </div>
</form>
<div class="containerArticles">
    <c:forEach var="a" items="${articles}">
        <div class="containerArticle card">
            <c:if test="${sessionScope.user != null }">
                <a href="article?idArticle=${a.id}" class="hrefArticle"></a>
            </c:if>
            <img class="card-img-top" src="data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxISEhUSEhIVFRUVFRUVFRUVFRUVFRUVFRUXFhUVFRUYHSggGBolHRUVITEhJSkrLi4uFx8zODMtNygtLisBCgoKDQ0NFQ8PFSsZFRkrLSstLSsrLS0tKy0rKystKystKzcrNy0tNzc3Ky03Nzc3KystKy0rKysrKysrKysrK//AABEIAMIBBAMBIgACEQEDEQH/xAAYAAEBAQEBAAAAAAAAAAAAAAAAAQIDB//EABgQAQEBAQEAAAAAAAAAAAAAAAABEQIx/8QAFwEBAQEBAAAAAAAAAAAAAAAAAAECA//EABcRAQEBAQAAAAAAAAAAAAAAAAABMRH/2gAMAwEAAhEDEQA/APHEVGnQClQAFRKAAggKloAhSggACAYoAAAYCKgIKgopBAWCQqgqFoAII7iGsOiotQQDQDUABGkBKUKCFKAIqCACgAAAAgoIqFVAAFRSqIUAEVFTjqRFlc2wNJQVBNBRC0FTAAQABNAABAEBRKKKgaAAIAQABQAAENAFRR0E0YaaqGgKmiAoICpoAaIAAlEUQ0FQAAFAEEUAA0AAFAEoFVDQVEtAdECMqtoigaaAAQA1AAKiggABoACKCAKgAAAAaEEVFQUAUANETQAbEGWlABRAAEBUAAAEAAAEAAAFAEBRFAIAAAgBigCABgDYgiqAiotNAAQDAAQUEEAAAAEUVBQBFBFNAAAAFQAABAWQQFaAZFEUUEAVFTAVAUAERAAAQFQVQQAUEBQQFAAoCogpQEqoAADQCKKigAgoKCIAKBURABQABFABFAAAAAAgqAAAAIKlBdQBWgVAARRFFEFAQUBEUBBURBFFBFAAARQAAAAgAAAQVEFASgA0oI0ECQAAQAFRQBAAEVKIAAAAAAACAAoAqAAAAAAIGijQpjKgAAICggKgACAKgAAABgACAoAhoigAKAQAAAAUKADU8IDLQAqUKDKpCgqBAWaACKgAhBACgCKgCqAoHKCBQCixYChUQVFpAFh0Aiv/2Q==" alt="img">
            <div class="card-body">
                <h5 class="card-title">${a.name}</h5>
                <p class="card-text">${a.description}</p>
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Prix : ${a.initialPrice} </li>
                <li class="list-group-item">Fin de l'enchere :<span class="dateEnd">${a.dateEndBid}</span></li>
                <c:if test="${a.stateSale =='IS_WIN' }">
                    <li class="list-group-item">Gagnant : <a href="profile?idUser=${a.user.id}">${a.user.username}</a>
                    </li>
                </c:if>
                <c:if test="${a.stateSale =='TO_SALE' }">
                    <li class="list-group-item">Vendeur : <a href="profile?idUser=${a.user.id}">${a.user.username}</a>
                    </li>
                </c:if>
                <c:if test="${a.stateSale =='NOT_START' }">
                    <li class="list-group-item">Vendeur : <a href="profile?idUser=${a.user.id}">${a.user.username}</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </c:forEach>
</div>
${$params}
<c:out value="${$params}"></c:out>
</body>
<script>
    let cat =${catSelected};
    let name = '${nameArticle}';

    cat !== -1 ? $('#category').val(cat) : null;
    name ? $('#name').val(name) : null;

    let typeBid = '${typeBid}';
    typeBid != '' ? $('#' + typeBid).attr('checked', 'checked') : null;


    let firstParam = '${firstParam}';
    let secondParam = '${secondParam}';
    let thirdParam = '${thirdParam}';

    if (typeBid === "typeBidMySell") {
        isTypeBidMySell();
        if (firstParam === "on") {
            $('#mySellOpen').prop('checked', true);
        }
        if (secondParam === "on") {
            $('#mySellNotStart').prop('checked', true);
        }
        if (thirdParam === "on") {
            $('#mySellFinish').prop('checked', true);
        }

    } else if (typeBid === "typeBidPurchase") {
        isTypeBidPurchase()
        if (firstParam === "on") {
            $('#bidOpen').prop('checked', true);
        }
        if (secondParam === "on") {
            $('#myBids').prop('checked', true);
        }
        if (thirdParam === "on") {
            $('#myWinBids').prop('checked', true);
        }
    }

    $('.dateEnd').each(function () {
        text = $(this).text().split('T')[0]
        $(this).text(text);
    });

    $('input[type=radio][name=typeBid]').change(function () {
        if ($(this).val() === "typeBidMySell") {
            isTypeBidMySell();
        } else if ($(this).val() === "typeBidPurchase") {
            isTypeBidPurchase();
        }
    });

    function isTypeBidMySell() {
        $('#checkboxsMySell').removeClass("filterCheckbox");
        $('#checkboxsPurchase').addClass("filterCheckbox");

        $('#checkboxsMySell input').each(function () {
            $(this).attr('disabled', false);
        });

        $('#checkboxsPurchase input').each(function () {
            $(this).prop('checked', false);
            $(this).attr('disabled', true);
        });
    }

    function isTypeBidPurchase() {
        $('#checkboxsMySell').addClass("filterCheckbox");
        $('#checkboxsPurchase').removeClass("filterCheckbox");

        $('#checkboxsMySell input').each(function () {
            $(this).prop('checked', false);
            $(this).attr('disabled', true);
        });

        $('#checkboxsPurchase input').each(function () {
            $(this).attr('disabled', false);
        });
    }

</script>
</html>