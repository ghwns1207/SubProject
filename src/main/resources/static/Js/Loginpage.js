'use strict';

//let button = document.getElementById('loginBtn');


function loginSub(e) {
   let id = document.getElementById('userId').value;
   let password = document.getElementById('userPW').value;
   console.log(id,password);
   e.preventDefault()
   $.ajax({
       type : 'POST',
       url : "mlogin",
       data : {userId: id, userPW : password},
       success : function(resultData) {
        console.log("일단 여기까지 성공", resultData.status);
        if(resultData.code === 200){
            alert("로그인 성공");
        }},
        error : function(resultData){
            console.log("실패");
            console.log(resultData.status);
        }
   });
}