function addProductToCart(id, title) {
    let csrfToken = sessionStorage.getItem("csrfToken")
    $.ajax(
        {
            type: "POST",
            url: "/rest/catalog",
            data: $.param({productId: id}),
            headers: {'X-CSRF-Token': csrfToken},
            success: function (data) {
                if (data === "success") { notify(title) }
            }
        }
    )
}

function notify(title) {
    Swal.fire({
        text: title + " was successfully added to your cart",
        position: "bottom-end",
        allowOutsideClick: true,
        allowEscapeKey: false,
        allowEnterKey: false,
        showConfirmButton: false,
        showCancelButton: false,
        timer: 2000
    });
}

function filter(filterParam) {
    $.ajax(
        {
            type: "GET",
            url: "/rest/catalog",
            data: $.param({filter: filterParam}),
            success: function (data) {
                console.log(data)
                displayFilterResult(data)
            }
        }
    )
}

function unordered(cartId) {
    $.ajax(
        {
            type: "GET",
            url: "/rest/catalog/unordered",
            data: $.param({cartId: cartId}),
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
    let list = $('.container')
    list.empty()

    if (result.length > 0) {
        result.forEach(function (product) {
            let card = $('<li>').addClass('card')
            let cardContent = $('<div>').addClass('scale')
            let image = $('<img>').addClass('card-img-top').attr('src', product.imageUrl)
            let cardBody = $('<div>').addClass('card-body')
            let category = $('<h6>').addClass('card-text').html(product.productCategory)
            let description = $('<h6>').addClass('card-text', 'text-muted').html(product.description)
            let title = $('<h5>').addClass('card-title').html(product.title)
            let price = $('<h5>').addClass('card-text').html(product.price + ' ₽')
            let button = $('<button>').addClass('btn btn-outline-dark').text('Add to cart').click(function () {
                addProductToCart(product.id, product.title)
            })

            cardBody.append(title);
            cardBody.append(category)
            cardBody.append(description);
            cardContent.append(image);
            cardContent.append(cardBody);
            card.append(cardContent);
            list.append(card);
            cardBody.append(price)
            cardContent.append(button)
        });
    } else {
        list.append('<li class="card">No results found</li>')
    }
}
