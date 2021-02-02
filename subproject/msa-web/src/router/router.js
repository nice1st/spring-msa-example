const routes = {
    '#about': document.createElement('page-about'),
}

// render
function renderHTML(el, child) {
    el.contents = child;
}

export function pushRoute(el) {
    renderHTML(el, routes[window.location.hash]);
}