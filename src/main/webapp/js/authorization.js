


function checkloggedin(){

    console.log(sessionStorage.getItem("myJwt"))
    console.log(sessionStorage.getItem("myJwt")==null);
    if(sessionStorage.getItem("myJwt")==null){window.location.assign("./login.html")}
}

checkloggedin();
