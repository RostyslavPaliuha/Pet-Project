$("#header").load("./components/header/header.html ");
$.ajaxSetup({

    headers: {'Authentication':sessionStorage.getItem('token')}
});

function decodeToken(token) {
    var playload = JSON.parse(atob(token.split('.')[1]));
    console.log(playload);

}
