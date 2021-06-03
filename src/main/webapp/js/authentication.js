const form = document.querySelector("#login-form");


checkloggedin();


function checkloggedin(){
    if(sessionStorage.getItem("myJwt")!=null){window.location.assign("../homepage.html")}
}


form.addEventListener("submit",evt => {
    evt.preventDefault()
    let fd = new FormData(this);

    fetch("login",{body:JSON.stringify(fd),method:"POST",
        headers:{"content-type":"application/json"}}).then(function (response){
            if(!response.ok){throw  new Error(response.status);}
            return  response.json();
    })
        .then(myJson=>{sessionStorage.setItem("myJwt",myJson.Authorization); window.location.reload()})
})
