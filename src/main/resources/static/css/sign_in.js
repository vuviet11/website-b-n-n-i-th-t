let btnf1 = document.getElementById('switch-btn1');
let btnf2 = document.getElementById('switch-btn2');
let login_form = document.getElementById('login-form');
let reg_form = document.getElementById('reg-form');
let btnLogout = document.getElementById('btnLogout');

btnf1.onclick = function() {
   login_form.style.display = 'none';
   reg_form.style.display = 'block';
}




