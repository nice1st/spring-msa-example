export default class Contents extends HTMLElement {
    constructor() {
        super();
        
        const shadow = this.attachShadow({mode: 'open'});
        shadow.innerHTML = require('./template/contents.html');

        const style = document.createElement('style');
        shadow.appendChild(style);

        this.initEvent();
    }
    
    async connectedCallback() {
        console.log('Custom square element added to page.');
    }

    disconnectedCallback() {
        console.log('Custom square element removed from page.');
    }

    adoptedCallback() {
        console.log('Custom square element moved to new page.');
    }

    static get observedAttributes() {
        return [];
    }
    attributeChangedCallback(name, oldValue, newValue) {
        console.log('Custom square element attributes changed.');
    }

    initEvent() {
        console.log("text");
    }

    /**
     * @param {HTMLElement} el
     */
    set contents(el) {
        this.shadowRoot.querySelector("#container").innerHTML = "";
        this.shadowRoot.querySelector("#container").append(el);
    }
}