export default class Menu extends HTMLLinkElement {
    constructor() {
        super();
        
        this.attachShadow({mode: 'open'});
        initEvent();
    }
    
    async connectedCallback() {
        console.log('Custom element added to page.');
    }

    disconnectedCallback() {
        console.log('Custom element removed from page.');
    }

    adoptedCallback() {
        console.log('Custom element moved to new page.');
    }

    static get observedAttributes() {
        return ["state"];
    }
    attributeChangedCallback(name, oldValue, newValue) {
        console.log('Custom element attributes changed.');
        switch (name) {
            case "state":
                this.changeAttrState(newValue);
                break;
        
            default:
                break;
        }
    }

    initEvent() {
        console.log("text");

        // this.shadowRoot.querySelector("").addEventListener("click", () => {});
    }

    changeAttrState(strState) {
        switch (strState) {
            case "active":
                this.className = strState;
                break;
            case "disable":
                this.className = strState;
                break;
        
            default:
                this.className = "enable";
                break;
        }
    }
}

customElements.define('component-menu', Menu, {extends: 'a'});