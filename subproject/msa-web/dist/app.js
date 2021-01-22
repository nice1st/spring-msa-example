/*
 * ATTENTION: The "eval" devtool has been used (maybe by default in mode: "development").
 * This devtool is not neither made for production nor for readable output files.
 * It uses "eval()" calls to create a separate source file in the browser devtools.
 * If you are trying to read the output file, select a different devtool (https://webpack.js.org/configuration/devtool/)
 * or disable the default devtool with "devtool: false".
 * If you are looking for production-ready output files, see mode: "production" (https://webpack.js.org/configuration/mode/).
 */
/******/ (() => { // webpackBootstrap
/******/ 	var __webpack_modules__ = ({

/***/ "./src/index.js":
/*!**********************!*\
  !*** ./src/index.js ***!
  \**********************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var _router_router_js__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ./router/router.js */ \"./src/router/router.js\");\n// router\n // app division\n\nvar appDiv = document.querySelector('div#app'); // Hash History\n\n(0,_router_router_js__WEBPACK_IMPORTED_MODULE_0__.pushRoute)(appDiv);\n\nwindow.onload = function () {\n  // route #anything\n  window.addEventListener(\"hashchange\", function () {\n    (0,_router_router_js__WEBPACK_IMPORTED_MODULE_0__.pushRoute)(appDiv);\n  }, false);\n};\n\n//# sourceURL=webpack://msa-web/./src/index.js?");

/***/ }),

/***/ "./src/router/router.js":
/*!******************************!*\
  !*** ./src/router/router.js ***!
  \******************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony export */ __webpack_require__.d(__webpack_exports__, {\n/* harmony export */   \"pushRoute\": () => /* binding */ pushRoute\n/* harmony export */ });\nvar routes = {\n  '': __webpack_require__(/*! ../page/about.html */ \"./src/page/about.html\"),\n  '#login': __webpack_require__(/*! ../page/login.html */ \"./src/page/login.html\"),\n  '#home': __webpack_require__(/*! ../page/home.html */ \"./src/page/home.html\")\n}; // render\n\nfunction renderHTML(el, route) {\n  el.innerHTML = route;\n}\n\nfunction pushRoute(el) {\n  renderHTML(el, routes[window.location.hash]);\n}\n\n//# sourceURL=webpack://msa-web/./src/router/router.js?");

/***/ }),

/***/ "./src/page/about.html":
/*!*****************************!*\
  !*** ./src/page/about.html ***!
  \*****************************/
/***/ ((module) => {

eval("// Module\nvar code = \"<body>\\r\\n    <h2>Example</h2>\\r\\n</body>\\r\\n\";\n// Exports\nmodule.exports = code;\n\n//# sourceURL=webpack://msa-web/./src/page/about.html?");

/***/ }),

/***/ "./src/page/home.html":
/*!****************************!*\
  !*** ./src/page/home.html ***!
  \****************************/
/***/ ((module) => {

eval("// Module\nvar code = \"\";\n// Exports\nmodule.exports = code;\n\n//# sourceURL=webpack://msa-web/./src/page/home.html?");

/***/ }),

/***/ "./src/page/login.html":
/*!*****************************!*\
  !*** ./src/page/login.html ***!
  \*****************************/
/***/ ((module) => {

eval("// Module\nvar code = \"<body>\\r\\n    <h2>LOGIN</h2>\\r\\n    <section>\\r\\n        <form onsubmit=\\\"onLogin(event)\\\">\\r\\n            <label for=\\\"id\\\">First name:</label><br>\\r\\n            <input type=\\\"text\\\" id=\\\"id\\\" name=\\\"userId\\\" value=\\\"admin\\\"><br>\\r\\n            <label for=\\\"password\\\">Last name:</label><br>\\r\\n            <input type=\\\"password\\\" id=\\\"password\\\" name=\\\"password\\\" value=\\\"admin123\\\"><br><br>\\r\\n            <input type=\\\"submit\\\" value=\\\"Submit\\\">\\r\\n        </form> \\r\\n        <button onclick=\\\"onRefresh(event)\\\">Refresh</button>\\r\\n    </section>\\r\\n</body>\\r\\n\\r\\n<script>\\r\\n    onLogin = (event) => {\\r\\n        event.preventDefault();\\r\\n        service.AuthService.getInstance().onLogin(document.getElementById(\\\"id\\\").value, document.getElementById(\\\"password\\\").value)\\r\\n        .then(res => console.log(res))\\r\\n        .catch(error => console.log(error));\\r\\n    }\\r\\n\\r\\n    onRefresh = (event) => {\\r\\n        service.AuthService.getInstance().onSilentRefresh()\\r\\n        .then(res => console.log(res))\\r\\n        .catch(error => console.log(error));\\r\\n    }\\r\\n</script>\\r\\n\";\n// Exports\nmodule.exports = code;\n\n//# sourceURL=webpack://msa-web/./src/page/login.html?");

/***/ })

/******/ 	});
/************************************************************************/
/******/ 	// The module cache
/******/ 	var __webpack_module_cache__ = {};
/******/ 	
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/ 		// Check if module is in cache
/******/ 		if(__webpack_module_cache__[moduleId]) {
/******/ 			return __webpack_module_cache__[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = __webpack_module_cache__[moduleId] = {
/******/ 			// no module.id needed
/******/ 			// no module.loaded needed
/******/ 			exports: {}
/******/ 		};
/******/ 	
/******/ 		// Execute the module function
/******/ 		__webpack_modules__[moduleId](module, module.exports, __webpack_require__);
/******/ 	
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/ 	
/************************************************************************/
/******/ 	/* webpack/runtime/define property getters */
/******/ 	(() => {
/******/ 		// define getter functions for harmony exports
/******/ 		__webpack_require__.d = (exports, definition) => {
/******/ 			for(var key in definition) {
/******/ 				if(__webpack_require__.o(definition, key) && !__webpack_require__.o(exports, key)) {
/******/ 					Object.defineProperty(exports, key, { enumerable: true, get: definition[key] });
/******/ 				}
/******/ 			}
/******/ 		};
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/hasOwnProperty shorthand */
/******/ 	(() => {
/******/ 		__webpack_require__.o = (obj, prop) => Object.prototype.hasOwnProperty.call(obj, prop)
/******/ 	})();
/******/ 	
/******/ 	/* webpack/runtime/make namespace object */
/******/ 	(() => {
/******/ 		// define __esModule on exports
/******/ 		__webpack_require__.r = (exports) => {
/******/ 			if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 				Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 			}
/******/ 			Object.defineProperty(exports, '__esModule', { value: true });
/******/ 		};
/******/ 	})();
/******/ 	
/************************************************************************/
/******/ 	// startup
/******/ 	// Load entry module
/******/ 	__webpack_require__("./src/index.js");
/******/ 	// This entry module used 'exports' so it can't be inlined
/******/ })()
;