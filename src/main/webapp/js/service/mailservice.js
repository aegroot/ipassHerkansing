const token=sessionStorage.getItem("myJwt");

export  default class MailService{
    constructor() {}

    static fetchInbox(){
        return fetch(`http://localhost:8080/mail/`,{method:'get',headers:{"Authorization":token}})
            .then((response)=>{
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();

            })
    }
    static fetchSent(){
        fetch("mail/sent",{method:"GET",
            headers:{"Authorization":token}})
            .then((response)=>{
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();

            })
    }
    static fetchMail(id){
        fetch(`http://localhost:8080/mail/${id}`,{method:"GET",
            headers:{"Authorization":token}})
            .then((response)=>{
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();

            })
    }
    static getSent(){
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

}
