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

            let data = new FormData();

            data.append("sender", "someone@example.com")
            data.append("recipient", mailList[i])
            data.append("message", fd.get("message"))
            data.append("title", fd.get("title"))


            fetch('http://localhost:8080/mail', {
                method: 'post', body: JSON.stringify(reqbody)
                , headers: {"content-type": "application/json","Authorization":sessionStorage.getItem("myJwt")}
            })
                .then(response => response.json())
                .then(status => {
                    console.log(status);
                })


        }


    })


function addMail() {
    mailLabel.innerHTML += ` <input type="email" class="email" name="recipient">`;
    console.log("test");
}





