const routes = {
    '': require('../page/about.html'),
    '#login': require('../page/login.html'),
    '#home': require('../page/home.html'),
}

// render
function renderHTML(el, route) {
    el.innerHTML = route;
}

export function pushRoute(el) {
    renderHTML(el, routes[window.location.hash]);
}