const form = document.querySelector("form#login-form");



form.addEventListener("submit",evt => {
    evt.preventDefault()
    let fd = new FormData(form);

    const body={"username":fd.get("username"),"password":fd.get("password")}
    //const body={"username":"anyone@example.com","password":"uu44vv"}

    console.log(Array.from(fd),body)
   fetch("login",{body:JSON.stringify(body),method:"POST",
        headers:{"content-type":"application/json"}}).
    then(function (response){
            if(!response.ok){throw  new Error(response.status);}
       sessionStorage.setItem("myJwt",response.headers.get("Authorization"))
       console.log(sessionStorage.getItem("myJwt"))
           window.location="../index.html"
    })
        .catch(error=>{console.log(error.message)})

})
