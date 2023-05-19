let cartItems = new Map()

function onLoad(productsInCart) {
    for (let i = 0; i < productsInCart.length; i++) {
        let item = Object.values(productsInCart[i])
        cartItems.set(item[0], {price: item[3], amount: item[5]})
    }

    updateTotalSum()
    document.getElementById("totalAmount").innerHTML = "Items in cart: " + cartItems.size
    isCartEmpty()
}

function updateTotalSum() {
    let result = 0

    for (let item of cartItems.entries()) {
        result += Object.values(item).at(1).price * Object.values(item).at(1).amount
    }

    document.getElementById("totalPrice").innerHTML = "Cart price: " + result.toFixed(1) + " ₽"
}

function updateProductPrice(productId) {
    let price = cartItems.get(productId).amount * cartItems.get(productId).price
    document.getElementById('itemPrice' + productId).innerHTML = price.toFixed(1) + " ₽"
}

function updateProductAmount(productId, newAmount) {
    cartItems.get(productId).amount = newAmount
}

function deleteProductFromCart(id, csrfToken) {
    $.ajax(
        {
            type: "DELETE",
            url: "/cart",
            data: $.param({productId: id}),
            headers: {'X-CSRF-Token': csrfToken}
        }
    )

    document.getElementById('card' + id).remove()
    cartItems.delete(id)
    document.getElementById("totalAmount").innerHTML = "Items in cart: " + cartItems.size
    updateTotalSum()
    isCartEmpty()
}

function onPlusClicked(productId, csrfToken) {
    let data = {btnPlus: productId, btnMinus: ""}
    $.ajax(
        {
            type: "PUT",
            url: "/cart",
            data: $.param(data),
            headers: {'X-CSRF-Token': csrfToken}
        }
    )

    let itemAmount = cartItems.get(productId).amount + 1
    document.getElementById('btnPlus' + productId).nextElementSibling.innerHTML = itemAmount
    updateProductAmount(productId, itemAmount)
    updateTotalSum()
    updateProductPrice(productId)
}

function onMinusClicked(productId, csrfToken) {
    let data = {btnPlus: "", btnMinus: productId}
    $.ajax(
        {
            type: "PUT",
            url: "/cart",
            data: $.param(data),
            headers: {'X-CSRF-Token': csrfToken}
        }
    )

    let itemAmount = cartItems.get(productId).amount - 1
    document.getElementById('btnMinus' + productId).previousElementSibling.innerHTML = itemAmount
    updateProductAmount(productId, itemAmount)
    updateTotalSum()
    updateProductPrice(productId)

    if (itemAmount === 0) {
        deleteProductFromCart(productId, csrfToken)
    }
}

function isCartEmpty() {
    if (cartItems.size === 0) {
        document.getElementById("toOrder").style.visibility = "hidden"
    }
}
