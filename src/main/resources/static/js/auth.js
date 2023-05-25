function handleSignInError() {
    let error = document.getElementById("hidden-error").value
    let email = document.getElementById("hidden-email").value

    switch (error) {
        case 'wrong-email':
            document.getElementById("wrong-email").innerText = "Wrong email"
            document.getElementById("email").value = email
            break
        case 'wrong-password':
            document.getElementById("wrong-password").innerText = "Wrong email"
            document.getElementById("email").value = email
            break
    }
}

function handleSignUpError() {
    let error = document.getElementById("hidden-error").value
    let email = document.getElementById("hidden-email").value
    let firstName = document.getElementById("hidden-first-name").value
    let secondName = document.getElementById("hidden-second-name").value

    switch (error) {
        case 'email-already-taken':
            document.getElementById("wrong-email").innerText = "Email already taken"
            document.getElementById("firstName").value = firstName
            document.getElementById("secondName").value = secondName
            document.getElementById("email").value = email
            break
        case 'weak-password':
            document.getElementById("wrong-password").innerText = "Weak password. Use at least 6 symbols"
            document.getElementById("firstName").value = firstName
            document.getElementById("secondName").value = secondName
            document.getElementById("email").value = email
            break
    }
}
