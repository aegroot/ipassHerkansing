import MailService from "../service/mailservice.js";
const mailService=new MailService()

var mailLabel = document.querySelector(".mailContainer");
const form = document.querySelector(".form");


form.addEventListener("submit",
    function (e) {
        e.preventDefault()
        let fd = new FormData(this);
        console.log(Array.from(fd))
        const mailList = fd.getAll("recipient")


        for (let i = 0; i < mailList.length; i++) {
            console.log(mailList[i])
            const reqbody = {
                "recipient": mailList[i],
                "title": fd.get("title"),
                "message": fd.get("message")
            }

            mailService.doSend(reqbody)
                .then(function (){})
                .catch(error=>{})



        }


    })


function addMail() {
    mailLabel.innerHTML += ` <input type="email" class="email" name="recipient">`;
    console.log("test");
}





