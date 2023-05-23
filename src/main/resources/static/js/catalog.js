function addProductToCart(id) {
    let csrfToken = sessionStorage.getItem("csrfToken")
    $.ajax(
        {
            type: "POST",
            url: "/rest/catalog",
            data: $.param({productId: id}),
            headers: {'X-CSRF-Token': csrfToken}
        }
    )
}

function filter(filterParam) {
    $.ajax(
        {
            type: "GET",
            url: "/rest/catalog",
            data: $.param({filter: filterParam}),
            success: function (data) {
                displayFilterResult(data)
            }
        }
    )
}

function getCsrfToken(csrfToken) {
    console.log(csrfToken)
    sessionStorage.setItem("csrfToken", csrfToken)
}

function displayFilterResult(result) {
    let list = $('.list-item');
    list.empty()

    if (result.length > 0) {
        result.forEach(function (product) {
            let item = $('<li class="card"></li>');
            let media = $('<div class="scale"></div>');
            let image = $('<img src="' + product.imageUrl + '" class="card-img-top">');
            let cardBody = $('<div class="card-body"></div>');
            let category = $('<h6 class="card-text">' + product.productCategory + '</h6>')
            let description = $('<h6 class="card-text text-muted">' + product.description + '</h6>')
            let title = $('<h5 class="card-title">' + product.title + '</h5>');
            let price = $('<h5 class="card-text">' + product.price + ' ₽' + '</h5>');
            let button = $('<button class="btn btn-outline-dark" onclick="addProductToCart(' + product.id + ')">' + 'Add to cart' + '</button>');

            cardBody.append(title);
            cardBody.append(category)
            cardBody.append(description);
            media.append(image);
            media.append(cardBody);
            item.append(media);
            list.append(item);
            cardBody.append(price)
            media.append(button)
        });
    } else {
        list.append('<li class="card">No results found</li>')
    }
}
