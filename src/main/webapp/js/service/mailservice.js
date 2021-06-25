const token=sessionStorage.getItem("myJwt");

export  default class MailService{
    constructor() {}

    fetchSend(reqbody){
       return  fetch('http://localhost:8080/mail', {
            method: 'post', body: JSON.stringify(reqbody)
            , headers: {"content-type": "application/json","Authorization":token}
        })
    }

     fetchInbox(){
        return fetch(`http://localhost:8080/mail/`,{
            method:'get',headers:{"Authorization":token}})
            .then((response)=>{
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();

            })
    }


    fetchSent(){
       return  fetch("mail/sent",{method:"GET",
            headers:{"Authorization":token}})
            .then((response)=>{
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();

            })
    }
     fetchMail(id){
        return fetch(`http://localhost:8080/mail/${id}`,{method:"GET",
            headers:{"Authorization":token}})
            .then((response)=>{
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();

            })
    }
     getSent(){
        return new Promise(((resolve, reject) => {
            this.fetchSent()
                .then((response) => {resolve(response); })
                .catch((error) => { reject(error); });

        }))
    }
    getInbox(){
        return new Promise(((resolve, reject) => {
            this.fetchInbox()
                .then((response) => {resolve(response); })
                .catch((error) => { reject(error); });

        }))
    }
    getMail(id){
        return new Promise(((resolve, reject) => {
            this.fetchMail(id)
                .then((response) => {resolve(response); })
                .catch((error) => { reject(error); });

        }))

    }
    doSend(reqbody){
        return new Promise(((resolve, reject) => {
            this.fetchSend(reqbody)
                .then((response) => {resolve(response); })
                .catch((error) => { reject(error); });

        }))
    }

}
