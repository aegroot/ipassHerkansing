
checkloggedin();

function checkloggedin(){

    console.log(sessionStorage.getItem("myJwt"))
    if(sessionStorage.getItem("myJwt")==null){window.location.assign("./login.html")}
}
