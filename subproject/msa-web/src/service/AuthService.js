import FetchUtil, {AUTH_URL} from '../util/FetchUtil';

export default class AuthService {
    static instance = null;

    static getInstance() {
        if (AuthService.instance == null) {
            AuthService.instance = new AuthService();
        }

        return this.instance;
    }

    onAuth = (id, password) => {
        return fetch(`${AUTH_URL}/oauth/token`, {
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
        }).then(res => res.json());
    }
}