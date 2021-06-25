const token=sessionStorage.getItem("myJwt");

export default class Blockedservice{
    constructor() {
    }

    fetchBlockUser(id){
        return fetch(`blocked/${id}`,{method:'post',
            headers:{"Authorization":token}})
            .then((response)=>{
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();

            })

    }
    fetchRemoveFromBlocked(id){
        return fetch(`blocked/${id}`,{method:'delete',
            headers:{"Authorization":token}})
            .then((response)=>{
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();

            })
    }

    fetchBlocked(){
        return fetch(`blocked`,{method:'get'
            ,headers:{"Authorization":token}})
            .then((response)=>{
                if (!response.ok) {
                    throw new Error(response.status);
                }
                return response.json();

            })

    }

    getBlocked(){
        return new Promise((resolve, reject) => {
            this.fetchBlocked()
                .then((response) => {resolve(response); })
                .catch((error) => { reject(error); });
        })
    }
    doRemoveFromBlocked(id){
        return new Promise((resolve, reject) => {
            this.fetchRemoveFromBlocked(id)
                .then((response) => {resolve(response); })
                .catch((error) => { reject(error); });
        })
    }
    doBlockUser(id){
        return new Promise((resolve, reject) => {
            this.fetchBlockUser(id)
                .then((response) => {resolve(response); })
                .catch((error) => { reject(error); });
        })

    }



}
