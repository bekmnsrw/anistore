function addProductToCart(id, csrfToken) {
    $.ajax(
        {
            type: "POST",
            url: "/catalog",
            data: $.param({productId: id}),
            headers: {'X-CSRF-Token': csrfToken}
        }
    )
}
