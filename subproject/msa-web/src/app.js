import {pushRoute} from './router/router.js'
import {About, Home} from './page/index.js'
import {Contents, Footer, Menubar} from './container/index.js'

const appDiv = document.querySelector('#contents');
window.onload = () => {
    window.addEventListener("hashchange", () => { pushRoute(appDiv) }, false);
    pushRoute(appDiv);
}

const register = () => {
    // custom element 를 여기서 해야 하나?
    customElements.define('container-menubar', Menubar);
    customElements.define('container-contents', Contents);
    customElements.define('container-footer', Footer);

    customElements.define('page-about', About);
    customElements.define('page-home', Home);
};

document.addEventListener("DOMContentLoaded", register);