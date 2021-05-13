
function fillableinbox() {
    const tbody=document.querySelector("tbody");

    fetch("mail/1",{method:'get'})
        .then(response=>Promise.all([response.status,response.json()]))
        .then(function ([status,myJson]){
            console.log(myJson)
            for(let i=0;i<myJson.length;i++){
                let titel = myJson[i].title;
                let datum = myJson[i].sendDate;
                let afzender = myJson[i].sender;
                tbody.innerHTML+=`<tr>
                                    <td>${titel}</td>
                                    <td>${datum}</td>
                                     <td>${afzender}</td>
                                   </tr>`;
            }
        })
}

fillableinbox();
