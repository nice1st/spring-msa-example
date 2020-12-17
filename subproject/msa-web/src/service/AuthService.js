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

        const response = fetch(`${AUTH_URL}/oauth/token`, {
            method: "POST",
            headers: {
                "Authorization": "Basic " + btoa("msa_auth_web:websecret"),
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: new URLSearchParams({
                "grant_type": "password",
                "scope": "write",
                "username": id,
                "password": password
            }).toString()
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