


function checkloggedin(){

    if(sessionStorage.getItem("myJwt")==null){window.location.assign("../login.html")}
}

checkloggedin();
