function fillTableBlocked(){


    const tbody=document.querySelector("tbody");
    tbody.innerHTML="";
    fetch(`blocked`,{method:'get',headers:{"Authorization":sessionStorage.getItem("myJwt")}})
        .then(response=>Promise.all([response.status,response.json()]))
        .then(function ( [status,myJson]) {


            console.log(myJson);
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
    if (!e.target.classList.contains("deleteBtn")) {
        return;
    }
    const button=e.target
    const mail=button.id

    fetch(`blocked/${mail}`,{method:'DELETE'
        ,headers: {"Authorization":sessionStorage.getItem("myJwt")}})
        .then(Response=>{
            if(!Response.ok){throw new Error(Response.status);} fillTableBlocked()

        })
        .catch(error=>{
            const header=document.getElementById("error-message")
            header.innerHTML=error.message;
        })



}


fillTableBlocked();
