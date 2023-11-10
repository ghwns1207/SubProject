
const userName = document.getElementById('userName');
const userBirthday = document.getElementById('userBirthday');
const userId = document.getElementById('userId');
const userPW = document.getElementById('userPW');
const userCheckPW = document.getElementById('userCheckPW');
const userPhoneNum = document.getElementById('userPhoneNum');
const userEmail = document.getElementById('userEmail');

const signupTable = document.getElementById('SingUpForm');


signupTable.addEventListener( 'keyup', (e) =>{
    const event = e.target;
    console.log(event.value);
    if(event == userName){
        console.log("username" , userName.value);
    }
});

function SingUpSub(e) {
    e.preventDefault();
    console.log(userPW, userBirthday, userEmail , userId, userCheckPW);
}

