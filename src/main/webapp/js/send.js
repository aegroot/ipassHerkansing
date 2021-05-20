var mailLabel=document.querySelector(".mailContainer");
const form=document.querySelector(".form");


form.addEventListener("submit",
    function (e) {
    e.preventDefault()
        let fd=new FormData(this);
        console.log(Array.from(fd))
        console.log(fd.getAll("recipient"))
})

function addMail(){mailLabel.innerHTML+=` <input type="email" class="email" name="recipient">`;
    console.log("test");}





