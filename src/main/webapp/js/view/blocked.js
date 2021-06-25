
import Blockedservice from "../service/blockedservice.js";

const blockedservice=new Blockedservice();

function fillTableBlocked(){


    const tbody=document.querySelector("tbody");
    tbody.innerHTML="";
    blockedservice.getBlocked()
        .then(function (myJson) {
            for(let i=0;i<myJson.length;i++){
                let naam=myJson[i].naam;
                let adres=myJson[i].mail;
                tbody.innerHTML+=`
                <tr>
                <td>${naam}</td>
                <td class="mailContainer">${adres}"</td>
                 <td><button class="deleteBtn" id=${myJson[i].mail}>unblock</button></td>
                </tr>`
            }
            document.querySelectorAll(".deleteBtn").forEach(e=>{e.addEventListener("click",removeFromBlocked)})

        })

}
function removeFromBlocked(e){
    console.log("test")

    if (!e.target.classList.contains("deleteBtn")) {
        return;
    }
    const button=e.target
    const mail=button.id

        blockedservice.doRemoveFromBlocked(mail)
        .then(function (){fillTableBlocked()}
        )
        .catch(error=>{
            const header=document.getElementById("error-message")
            header.innerHTML=error.message;
        })



}


fillTableBlocked();
