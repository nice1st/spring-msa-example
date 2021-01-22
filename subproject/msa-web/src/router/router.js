import {registerLogin} from '../page/login';

const routes = {
    '': require('../page/about.html'),
    '#login': registerLogin(),
    '#home': require('../page/home.html'),
}

// render
function renderHTML(el, route) {
    el.appendChild(route);
}

export function pushRoute(el) {
    renderHTML(el, routes[window.location.hash]);
}