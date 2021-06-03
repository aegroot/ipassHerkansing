function fillTableBlocked(){


    const tbody=document.querySelector("tbody");
    fetch(`blocked`,{method:'get',headers:{"Authorization":sessionStorage.getItem("myJwt")}})
        .then(response=>Promise.all([response.status,response.json()]))
        .then(function ( [myJson]) {
            console.log(myJson);
            for(let i=0;i<myJson.length;i++){
                let naam=myJson[i].naam;
                let adres=myJson[i].mail;
                tbody.innerHTML+=`
                <tr>
                <td>${naam}</td>
                <td class="mailContainer">${adres}</td>
                 <td><button class="deleteBtn">block</button></td>
                </tr>`
            }
            document.querySelector("table").addEventListener("click",removeFromBlocked);

        })

}
function removeFromBlocked(e){
    if (!e.target.classList.contains("deleteBtn")) {
        return;
    }
    const row=e.target.closest("tr")
    const mail=row.querySelector(".mailContainer");

    fetch(`blocked/${mail}`,{method:'DELETE'
        ,headers: {"Authorization":sessionStorage.getItem("myJwt")}})
        .then(Response=>{
            if(!Response.ok){throw new Error(Response.status);}window.location.reload()
        })



}


fillTableBlocked();
