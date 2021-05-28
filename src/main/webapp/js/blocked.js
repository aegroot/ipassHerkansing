function fillTableBlocked(){
    const tbody=document.querySelector("tbody");
    fetch(`blocked/someone@example.com`,{method:'get'})
        .then(response=>{Promise.all([response.status, response.json()])})
        .then(function ( [status,myJson]) {
            console.log(myJson);
            for(let object of myJson){
                let naam=object.naam;
                let adres=object.mail;
                tbody.innerHTML+=`
                <tr>
                <td>${naam}</td>
                <td>${adres}</td>
                </tr>`
            }

        })

}
fillTableBlocked();
