// function validate() {
//     // result boolean variable
//     var formIsOkay = true;
//     // find the first Name value, then repeat for other fields
//     var password = document.getElementById("password").value;
//
//     // store error message in variables
//     var passwordError = document.getElementById("password-error-message");
//     // reset previous error messages
//     var errorMessages = document.getElementsByTagName("span");
//     for (let i = 0; i < errorMessages.length; i++){
//         errorMessages[i].innerHTML = "";
//     }
//
//
//
//     // check if password is null
//     if (!password) {
//         passwordError.innerHTML = "Please enter a password";
//         formIsOkay = false;
//         document.getElementById("password").focus();
//     } else if (!password.replace(/\s/g, '').length) { // check if password is spaces only
//         passwordError.innerHTML = "Only spaces found";
//         formIsOkay = false;
//         document.getElementById("password").focus();
//     } else if (password.length < 8){ // check for password length
//         passwordError.innerHTML = "Password should be at least 8 characters long";
//         formIsOkay = false;
//         document.getElementById("password").focus();
//     } else if (!password.match(/[A-Z]/g)) {
//         passwordError.innerHTML = "Password requires at least 1 uppercase letter";
//         formIsOkay = false;
//         document.getElementById("password").focus();
//     } else if (!password.match(/[0-9]/g)) {
//         passwordError.innerHTML = "Password requires at least 1 number";
//         formIsOkay = false;
//         document.getElementById("password").focus();
//     }
//
//
//     return formIsOkay;
// }

function validateBook() {
    var formIsOkay = true;
    // Input
    var ISBN = document.getElementById("ISBN").value;
    var title = document.getElementById("title").value;
    var author = document.getElementById("author").value;
    var quantity = document.getElementById("quantity").value;
    var year = document.getElementById("year").value;
    var bookImage = document.getElementById("book_image").value;

    // Span Error Message
    var ISBN_error_message = document.getElementById("ISBN-error-message");
    var title_error_message = document.getElementById("title-error-message");
    var author_error_message = document.getElementById("author-error-message");
    var quantity_error_message = document.getElementById("quantity-error-message");
    var year_error_message = document.getElementById("year-error-message");
    var bookImage_error_message = document.getElementById("bookImage-error-message");

    //Clearing up Error messages
    var spanList = document.getElementsByTagName("span");
    for (let i=0; i < spanList.length; i++) {
        spanList[i].innerText = "";
    }

    if (!ISBN) {
        formIsOkay = false;
        ISBN_error_message.innerText = "Please enter book ISBN";
    }
    if (!title) {
        formIsOkay = false;
        title_error_message.innerText = "Please enter book title";
    }
    if (!author) {
        formIsOkay = false;
        author_error_message.innerText = "Please enter book author";
    }
    if (!quantity) {
        formIsOkay = false;
        quantity_error_message.innerText = "Please enter book quantity";
    }
    if (!year) {
        formIsOkay = false;
        year_error_message.innerText = "Please enter book year";
    }
    if (!bookImage) {
        formIsOkay = false;
        bookImage_error_message.innerText = "Please upload book Image";
    }





    return formIsOkay;
}
