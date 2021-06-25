import MailService from '../service/mailservice.js';
import Blockedservice from "../service/blockedservice.js";

const blockedservice=new Blockedservice();
const mailservice=new MailService();



function fillableinbox() {



    const tbody=document.querySelector("tbody");
    mailservice.getInbox()
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
                                     <td><button class="block" id="${myJson[i].sender}-delete">block</button></td>
                                     <td><button class="view" id="${myJson[i].id}-open">view</button></td>                                                                    
                                   </tr>`;
            }
            document.querySelectorAll(".block").forEach(e=>{e.addEventListener("click",blockUser)})
            document.querySelectorAll(".view").forEach(e=>{e.addEventListener("click",openModal)})
            document.querySelector("#close").addEventListener("click",closeModal)
        })
}

function blockUser(e){
    if (!e.target.classList.contains("block")) {

        console.log("no")
        return;
    }

    const button=e.target;
    const deleteid=button.id

    const id=deleteid.substring(0,deleteid.length-7)
    console.log(id)
    deleteid.substring()
        blockedservice.doBlockUser(id)
        .then(function (){button.style.color="green";})
            .catch(error=>{
                button.style.color="red";})



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
    const id=openid.split("-")[0]

    const dialog=document.querySelector("dialog")

        mailservice.getMail(id)
        .then(function (myJson){
            document.querySelector("#recipient").innerHTML=myJson.recipient
            document.querySelector("#message").innerHTML=myJson.message
            document.querySelector("#sender").innerHTML=myJson.sender
            document.querySelector("#title").innerHTML=myJson.title
            document.querySelector("#date").innerHTML=myJson.date
            dialog.show();


        })



}



