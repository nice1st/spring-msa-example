// router
import {pushRoute} from './router/router.js'

// app division
const appDiv = document.querySelector('div#app');

// Hash History
pushRoute(appDiv);

window.onload = () => {
    // route #anything
    window.addEventListener("hashchange", () => { pushRoute(appDiv) }, false)
}
