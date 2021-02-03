import {Login} from './page/index.js'

window.onload = () => {
    document.querySelector("page-login").addEventListener("login-success", (e) => {
        // window.location.href = "/main";
    });
}

const register = async () => {
    // custom element 를 여기서 해야 하나?
    customElements.define('page-login', Login);
};

document.addEventListener("DOMContentLoaded", register);
