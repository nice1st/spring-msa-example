export default class Footer extends HTMLElement {
    constructor() {
        super();
        
        // 엘리먼트의 기능들은 여기에 작성합니다.
        var shadow = this.attachShadow({mode: 'open'});
        shadow.innerHTML = require('./template/footer.html');

        var style = document.createElement('style');
        shadow.appendChild(style);
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
}