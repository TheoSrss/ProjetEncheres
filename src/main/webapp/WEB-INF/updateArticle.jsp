<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier mon article</title>
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
    <form method="POST" action="updateArticle">
        <div class="form-group">
            <label class="form-label" for="name">Nom :</label>
            <input type="text" id="name" name="name" class="form-control" value="${a.name}">
        </div>
        <div class="form-group">
            <label class="form-label" for="description">Description :</label>
            <input type="text" id="description" name="description" class="form-control" value="${a.description}">
        </div>
        <div class="form-group">
            <label class="form-label" for="category">Categories :</label>
            <select id="category" name="category" class="form-control">
                <c:forEach var="cat" items="${categories}">
                    <option value="${cat.id}">${cat.wording}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label class="form-label" for="dateStartBid">Date de début :</label>
            <input type="datetime-local" id="dateStartBid" name="dateStartBid" class="form-control"
                   value="${a.dateStartBid}">
        </div>
        <div class="form-group">
            <label class="form-label" for="dateEndBid">Date de fin :</label>
            <input type="datetime-local" id="dateEndBid" name="dateEndBid" class="form-control" value="${a.dateEndBid}">
        </div>
        <div class="form-group">
            <label class="form-label" for="initialPrice">Prix de départ :</label>
            <input type="number" id="initialPrice" name="initialPrice" class="form-control" value="${a.initialPrice}">
        </div>
        <div class="containerAddressDefault">
            <h5>Modalités de retrait</h5>
            <div class="form-group">
                <label class="form-label" for="street">Rue :</label>
                <input type="text" id="street" name="street" class="form-control" value="${a.withdrawal.street}">
            </div>
            <div class="form-group">
                <label class="form-label" for="postalCode">Code postal :</label>
                <input type="text" id="postalCode" name="postalCode" class="form-control"
                       value="${a.withdrawal.postalCode}">
            </div>
            <div class="form-group">
                <label class="form-label" for="city">Ville :</label>
                <input type="text" id="city" name="city" class="form-control" value="${a.withdrawal.city}">
            </div>
        </div>
        <input style="margin-top: 20px" type="submit" value="Créer" class="btn btn-primary">
    </form>
</div>
</body>
</html>
<script>
    let cat =${catSelected};

    cat !== -1 ? $('#category').val(cat) : null
</script>
