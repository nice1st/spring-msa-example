import {Login} from './page/index.js'

window.onload = () => {
}

const register = async () => {
    // custom element 를 여기서 해야 하나?
    customElements.define('page-login', Login);
};

document.addEventListener("DOMContentLoaded", register);
