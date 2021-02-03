import AuthService from './service/AuthService'

import {pushRoute} from './router/router.js'
import {About, Home} from './page/index.js'
import {Contents, Footer, Menubar} from './container/index.js'

// app 은 token 이 있는 상태로 접근 가능함. refresh 요청 갱신으로 확인
AuthService.getInstance().onRefresh().catch(error => window.location.href = "/login");

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