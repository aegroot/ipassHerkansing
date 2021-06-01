function fillTableBlocked(){

    const tbody=document.querySelector("tbody");
    fetch(`blocked/1`,{method:'get'})
        .then(response=>Promise.all([response.status,response.json()]))
        .then(function ( [status,myJson]) {
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
    const id=localStorage.getItem("id")
    const deleteBody=`{adder:${id},target:${mail}}`

    fetch('blocked',{method:'DELETE',deleteBody
        ,headers: {"content-type": "application/json"}})
        .then(Response=>{
            if(!Response.ok){throw new Error(Response.status);}
        })



}


fillTableBlocked();
