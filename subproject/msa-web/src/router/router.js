const routes = {
    '': document.createElement('page-about'),
    '#about': document.createElement('page-about'),
    '#home': document.createElement('page-home'),
}

// render
function renderHTML(el, child) {
    el.contents = child;
}

export function pushRoute(el) {
    renderHTML(el, routes[window.location.hash]);
}