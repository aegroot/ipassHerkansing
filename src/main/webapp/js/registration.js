const form=document.getElementById("registration-form")
const openButton=document.getElementById("register-open")
const submitButton=document.getElementById("register-submit")
const cancelButton=document.getElementById("register-cancel")

openButton.addEventListener("click",openModal)
submitButton.addEventListener("click",register)
cancelButton.addEventListener("click",closeModal)


form.addEventListener("submit",register)



function register(event){
    event.preventDefault()
    let fd = new FormData(form);
    const fbody=JSON.stringify(Array.from(fd))

    fetch("register",{body:fbody,method:"POST",
        headers:{"content-type":"application/json"
            ,"Authorization":sessionStorage.getItem("myJwt")}})




}

function openModal(event){


    const modal=document.querySelector("#register")
    modal.show()

}

function closeModal(){
    console.log("test")


    const dialog=document.querySelector("#register")
    dialog.close()
}


