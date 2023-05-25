function getCsrfToken(csrfToken) {
    console.log(csrfToken)
    sessionStorage.setItem("csrfToken", csrfToken)
}

function addProduct() {
    let name = document.getElementById("productName").value
    let description = document.getElementById("productDescription").value
    let price = document.getElementById("productPrice").value
    let category = document.getElementById("productCategory").value
    let imageUrl = document.getElementById("productImage").value

    if (name === "" || description === "" || price === "" || imageUrl === "") {
        document.getElementById("errorMessage").style.visibility = "visible"
        document.getElementById("errorMessage").innerText = "Fill all fields"
    } else {
        document.getElementById("errorMessage").style.visibility = "invisible"

        let csrfToken = sessionStorage.getItem("csrfToken")

        let product = {
            'title': name,
            'description': description,
            'imageUrl': imageUrl,
            'price': price,
            'category': category
        }

        $.ajax(
            {
                url: "/rest/products",
                method: "POST",
                data: JSON.stringify(product),
                contentType: 'application/json',
                headers: {'X-CSRF-Token': csrfToken},
                success: function (data) {
                    notify(
                        data.title + " was successfully added to db"
                    )
                    document.getElementById("productName").value = ""
                    document.getElementById("productDescription").value = ""
                    document.getElementById("productPrice").value = ""
                    document.getElementById("productCategory").value = ""
                    document.getElementById("productImage").value = ""
                }
            }
        )
    }
}

function updateProduct() {
    let id = document.getElementById("productId").value
    let name = document.getElementById("productName").value
    let description = document.getElementById("productDescription").value
    let price = document.getElementById("productPrice").value
    let category = document.getElementById("productCategory").value
    let imageUrl = document.getElementById("productImage").value

    if (id !== "") {
        let csrfToken = sessionStorage.getItem("csrfToken")

        let product = {
            'title': name,
            'description': description,
            'imageUrl': imageUrl,
            'price': price,
            'category': category
        }

        $.ajax(
            {
                url: '/rest/products/' + id,
                method: "PUT",
                data: JSON.stringify(product),
                contentType: 'application/json',
                headers: {'X-CSRF-Token': csrfToken},
                success: function (data) {
                    document.getElementById("errorMessage").style.visibility = "hidden"
                    notify(
                        data.title + " was successfully updated"
                    )
                    document.getElementById("update-form").reset()
                },
                error: function () {
                    document.getElementById("errorMessage").style.visibility = "visible"
                    document.getElementById("errorMessage").innerText = "No product with such ID"
                }
            }
        )
    } else {
        document.getElementById("errorMessage").style.visibility = "visible"
        document.getElementById("errorMessage").innerText = "Fill product's ID"
    }
}

function notify(title) {
    Swal.fire({
        text: title,
        position: "bottom-end",
        allowOutsideClick: true,
        allowEscapeKey: false,
        allowEnterKey: false,
        showConfirmButton: false,
        showCancelButton: false,
        timer: 2000
    });
}

function getPage() {
    let page = document.getElementById("page").value

    $.ajax(
        {
            type: "GET",
            url: "/rest/products",
            data: $.param({"page": page}),
            success: function (data) {
                console.log(data)
                getProducts(data.products)
            }
        }
    )
}

function deleteProduct(id) {
    let csrfToken = sessionStorage.getItem("csrfToken")

    $.ajax(
        {
            url: '/rest/products/' + id,
            method: "DELETE",
            headers: {'X-CSRF-Token': csrfToken},
            success: function () {
                notify(
                    "Product with id <" + id + "> was successfully deleted"
                )
                document.getElementById(id).remove()
            }
        }
    )
}

function getProducts(result) {
    let list = $('.container')
    list.empty();

    if (result.length > 0) {
        result.forEach(function (product) {
            let tr = $('<tr>').attr('id', product.id)
            let th = $('<th>').addClass('text-center').attr('scope', 'row').html(product.id)
            let td1 = $('<td>').addClass('text-center').html(product.title)
            let td2 = $('<td>').addClass('text-center').html(product.description)
            let td3 = $('<td>').addClass('text-center').html(product.price + ' ₽')
            let td4 = $('<td>').addClass('text-center').html(product.productCategory)
            let td5 = $('<td>').addClass('text-center')
            let buttonDelete = $('<button>').addClass('btn btn-outline-danger').text('Delete').click(function () {
                deleteProduct(product.id)
            })

            tr.append(th)
            tr.append(td1)
            tr.append(td2)
            tr.append(td3)
            tr.append(td4)
            tr.append(td5)
            td5.append(buttonDelete)
            list.append(tr)
        })
    }

}
