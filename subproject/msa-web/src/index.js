// router
import {pushRoute} from './router/router.js'

// app division
const appDiv = document.querySelector('div#app');

// Hash History
pushRoute(appDiv);

window.onload = () => {
    // route #anything
    window.addEventListener("hashchange", () => { pushRoute(appDiv) }, false);
}

const register = async () => {
    // custom element 를 여기서 해야 하나?
};

document.addEventListener("DOMContentLoaded", register);