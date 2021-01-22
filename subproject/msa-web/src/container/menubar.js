export class MenubarViewModel {
    constructor(){}

    
}

export default class Menubar extends HTMLElement {
    constructor() {
        super();
        
        this.attachShadow({mode: 'open'});
        initEvent();
    }
    
    async connectedCallback() {
        console.log('Custom square element added to page.');

        const response = await fetch("./templates/menubar.html");
        const template = await response.text();
        this.shadowRoot.innerHTML = template;
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

        // this.shadowRoot.querySelector("").addEventListener("click", () => {});
    }
}

customElements.define('container-menubar', Menubar);