const tbody=document.querySelector("tbody");


fetch("mail/sent",{method:"GET",
    headers:{"Authorization":sessionStorage.getItem("myJwt")}})
    .then(response=>Promise.all([response.status,response.json()]))
    .then(myJson=>{
        for(let i=0;i<myJson.length;i++){
            let titel = myJson[i].title;
            console.log(myJson[0]);
            let datum = myJson[i].sendDate;
            let afzender = myJson[i].sender;
            tbody.innerHTML+=`<tr>
                                    <td>${titel}</td>
                                    <td>${datum}</td>
                                    
                                     <td class="sender">${afzender}</td>
                                     <td hidden>${myJson[i].id}</td>
                                     <td><button class="view">view</button></td>                                               
                                   </tr>`;
        }
    })

function  viewMail(event,id){

}

