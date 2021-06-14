
function fillableinbox() {

    const tbody=document.querySelector("tbody");
    fetch(`http://localhost:8080/mail/`,{method:'get',headers:{"Authorization":sessionStorage.getItem("myJwt")}})
        .then(response=>response.json())
        .then(function (myJson){
            console.log(myJson)
            for(let i=0;i<myJson.length;i++){
                let titel = myJson[i].title;
                console.log(myJson[0]);
                let datum = myJson[i].date;
                let afzender = myJson[i].sender;
                tbody.innerHTML+=`<tr>
                                    <td>${titel}</td>
                                    <td>${datum}</td>
                                     <td class="sender">${afzender}</td>                                
                                     <td><button class="delete">block</button></td>
                                     <td><button class="view" id="${myJson[i].id}-open">view</button></td>                                           
                                   </tr>`;
            }
            document.querySelector(".delete").addEventListener("click",blockUser);
            document.querySelectorAll(".view").forEach(e=>{e.addEventListener("click",openModal)})
            document.querySelector("#close").addEventListener("click",closeModal)
        })
}

function blockUser(e){
    if (!e.target.classList.contains("delete")) {
        return;
    }

    const mail=e.target().querySelector(".sender");
    const id=sessionStorage.getItem("id")
    const deleteBody=`{adder:${id},target:${mail}}`
    fetch('blocked',{method:'post',body:JSON.stringify(deleteBody),
        headers:{"Authorization":sessionStorage.getItem("myJwt")}})
        .then(Response=>{
            if(!Response.ok){throw new Error(Response.status);}
        })


    console.log(mail.innerHTML);


}

fillableinbox();



function closeModal(){
    console.log("test")


    const dialog=document.querySelector("dialog")
    dialog.close()
}
function openModal(event){
    const button=event.target;
    const openid=button.id
    console.log(openid)
    const id=openid.split("-")[0]
    console.log(id)

    const dialog=document.querySelector("dialog")

    fetch(`http://localhost:8080/mail/${id}`,{method:"GET",
        headers:{"Authorization":sessionStorage.getItem("myJwt")}})
        .then(response=>response.json())
        .then(function (myJson){
            document.querySelector("#recipient").innerHTML=myJson.recipient
            document.querySelector("#message").innerHTML=myJson.message
            document.querySelector("#sender").innerHTML=myJson.sender
            document.querySelector("#title").innerHTML=myJson.title
            document.querySelector("#date").innerHTML=myJson.date
            dialog.show();


        })



}


