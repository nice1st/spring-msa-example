import AuthService from '../service/AuthService';

export default class Login extends HTMLElement {
    constructor() {
        // 항상 생성자에서 super는 처음으로 호출됩니다
        super();
    
        // 엘리먼트의 기능들은 여기에 작성합니다.
        var shadow = this.attachShadow({mode: 'open'});

        var div = document.createElement('div');
        div.innerHTML = require('./template/login.html');
        var style = document.createElement('style');
        shadow.appendChild(style);
        shadow.appendChild(div);

        this.initEvent();
    }

    // connectedCallback: Invoked when the custom element is first connected to the document's DOM.
    // disconnectedCallback: Invoked when the custom element is disconnected from the document's DOM.
    // adoptedCallback: Invoked when the custom element is moved to a new document.
    // attributeChangedCallback: Invoked when one of the custom element's attributes is added, removed, or changed.
    connectedCallback() {
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

        this.shadowRoot.querySelector("#login").addEventListener("click", this.onLogin);
        this.shadowRoot.querySelector("#refresh").addEventListener("click", this.onRefresh);
    }
    
    onLogin = (event) => {
        const self = this;
        AuthService.getInstance().onLogin(self.shadowRoot.querySelector("#id").value, self.shadowRoot.querySelector("#password").value)
        .then(res => {
            console.log(res);
            self.dispatchEvent(new CustomEvent("login-success"));
        })
        .catch(error => console.log(error));
    }

    onRefresh = (event) => {
        AuthService.getInstance().onSilentRefresh()
        .then(res => console.log(res))
        .catch(error => console.log(error));
    }
}