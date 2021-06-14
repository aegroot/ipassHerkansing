

const tbody=document.querySelector("tbody");


fetch("mail/sent",{method:"GET",
    headers:{"Authorization":sessionStorage.getItem("myJwt")}})
    .then(response=>response.json())
    .then(myJson=>{
        for(let i=0;i<myJson.length;i++){
            let titel = myJson[i].title;
            console.log(myJson[0]);
            let datum = myJson[i].date;
            let ontvanger = myJson[i].target;
            tbody.innerHTML+=`<tr>
                                    <td>${titel}</td>
                                    <td>${datum}</td>     
                                    <td>${ontvanger}</td>                        
                                     <td>${myJson[i].id}</td>
                                     <td><button class="view" id="${myJson[i].id}-open">view</button></td>                                               
                                   </tr>`;
        }
        document.querySelectorAll(".view").forEach(e=>{e.addEventListener("click",openModal)})
        document.querySelector("#close").addEventListener("click",closeModal)
    })

function closeModal(){


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
