
function fillableinbox() {
    sessionStorage.setItem("id","1")

    const tbody=document.querySelector("tbody");
    fetch(`http://localhost:8080/mail/${localStorage.getItem("id")}`,{method:'get',headers:{}})
        .then(response=>Promise.all([response.status,response.json()]))
        .then(function ([status,myJson]){
            console.log(myJson)
            for(let i=0;i<myJson.length;i++){
                let titel = myJson[i].title;
                console.log(myJson[0]);
                let datum = myJson[i].sendDate;
                let afzender = myJson[i].sender;
                tbody.innerHTML+=`<tr>
                                    <td>${titel}</td>
                                    <td>${datum}</td>
                                     <td class="sender">${afzender}</td>
                                     <td><button class="delete">block</button></td>
                                                   
                                   </tr>`;
            }
            document.querySelector("table").addEventListener("click",blockUser);
        })
}

function blockUser(e){
    if (!e.target.classList.contains("delete")) {
        return;
    }

    const mail=e.target().querySelector(".sender");
    const id=sessionStorage.getItem("id")
    const deleteBody=`{adder:${id},target:${mail}}`
    fetch('blocked',{method:'post',body:JSON.stringify(deleteBody)})
        .then(Response=>{
            if(!Response.ok){throw new Error(Response.status);}
        })


    console.log(mail.innerHTML);


}

fillableinbox();


