import {AUTH_URL, tocken} from '../util/FetchUtil';

export default class AuthService {
    static instance = null;

    static getInstance() {
        if (AuthService.instance == null) {
            AuthService.instance = new AuthService();
        }

        return AuthService.instance;
    }

    onLogin(id, password) {
        const self = this;

        const response = fetch(`${AUTH_URL}/oauth/token/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json;charset=UTF-8"
            },
            body: JSON.stringify({
                "id": id,
                "password": password
            })
        }).then(res => res.json())
        .then(self.onLoginSuccess)
        .catch(error => {
            // ... 로그인 실패 처리
        });

        return response;
    }
    
    onSilentRefresh() {
        const self = this;

        const response = fetch(`${AUTH_URL}/silent-refresh`, {
            // something
        }).then(self.onLoginSuccess)
        .catch(error => {
            // ... 로그인 실패 처리
        });
    }

    onLoginSuccess(response) {

        response.then(res => {
            tocken = res.access_token;
        })

        return response;
    }
}