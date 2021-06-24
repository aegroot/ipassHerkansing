const register_form=document.getElementById("registration-form")
const openButton=document.getElementById("register-open")
const submitButton=document.getElementById("register-submit")
const cancelButton=document.getElementById("register-cancel")
const errorMessage=document.querySelector("#registration-form .error")

const errorDialog=document.getElementById("error")


openButton.addEventListener("click",openModal)
submitButton.addEventListener("click",register)
cancelButton.addEventListener("click",closeModal)



function register(event){
    event.preventDefault()
    let fd = new FormData(register_form);

    const body={
        "username":fd.get("username"),
        "password":fd.get("password"),
        "firstName":fd.get("firstName"),
        "lastName":fd.get("lastName"),
        "gbday":fd.get("gbday"),
        "gbmonth":fd.get("gbmonth"),
        "gbyear":fd.get("gbyear")
    };

    console.log(body)

    fetch("register",{body:JSON.stringify(body),method:"POST",
        headers:{"content-type":"application/json"}})
        .then(function (response){
            if(!response.ok){
                throw  new Error(response.status)
            }
            register_form.style.backgroundColor="green"
            errorMessage.textContent="gelukt! u kunt nu inloggen"

        })
        .catch(error=>{


            register_form.style.backgroundColor="lightred"
            errorMessage.textContent="error"+error.message

        })




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


