// router
import {pushRoute} from './router/router.js'
import {About} from './page/index.js'
import {Contents, Footer, Menubar} from './container/index.js'

// app division
const appDiv = document.querySelector('#contents');

window.onload = () => {
    // route #anything
    window.addEventListener("hashchange", () => { pushRoute(appDiv) }, false);
    pushRoute(appDiv);
}

const register = async () => {
    // custom element 를 여기서 해야 하나?
    customElements.define('container-menubar', Menubar);
    customElements.define('container-contents', Contents);
    customElements.define('container-footer', Footer);

    customElements.define('page-about', About);
};

document.addEventListener("DOMContentLoaded", register);