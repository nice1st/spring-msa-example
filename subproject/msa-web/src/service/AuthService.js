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

        return fetch(`${AUTH_URL}/auth/token/login`, {
            method: "POST",
            credentials: 'include',
            headers: {
                "Content-Type": "application/json;charset=UTF-8",
            },
            body: JSON.stringify({
                "id": id,
                "password": password
            })
        }).then(self.onResponse);
    }
    
    onSilentRefresh() {
        const self = this;

        return fetch(`${AUTH_URL}/auth/token/refresh`, {
            method: "POST",
            credentials: 'include'
        }).then(self.onResponse);
    }

    onResponse(response) {
        if (response.status != 200) {
            throw response;
        }
        
        return response.json()
        .then(response => {
            try {
                FetchUtil.token = response.message;
            } catch (error) {
                console.error(error);
            }
            return response;
        });
    }
}