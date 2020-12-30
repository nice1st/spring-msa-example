import FetchUtil, {AUTH_URL} from '../util/FetchUtil';

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

        console.log(AUTH_URL);
        const response = fetch(`${AUTH_URL}/auth/token/login`, {
            method: "POST",
            credentials: 'include',
            headers: {
                "Content-Type": "application/json;charset=UTF-8",
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
        try {
            FetchUtil.token = response.message;
        } catch (error) {
            console.error(error);
        }
        return response;
    }
}