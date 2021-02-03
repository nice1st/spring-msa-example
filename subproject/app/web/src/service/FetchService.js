import AuthService from './AuthService';

export const BASE_URL = process.env.BASE_URL;
export const AUTH_URL = process.env.AUTH_URL;
export let TOKEN = undefined;

export default class FetchService {
    static instance = null;

    static getInstance() {
        if (FetchService.instance == null) {
            FetchService.instance = new FetchService();
        }

        return FetchService.instance;
    }

    set token(token) {
        TOKEN = token;
    }

    getBaseUrl(url) {
        return url.startsWith("http") ? "" : BASE_URL;
    }

    getHeaders(headers) {
        const defaultHeaders = {
            "Content-Type": "application/json; charset=UTF-8",
            "Authorization": TOKEN,
        };
        return Object.assign({}, defaultHeaders, headers);
    }

    get(url, param = {}, headers = {}, retry = 0) {
        const self = this;
        const _url = new URL(url, self.getBaseUrl(url));
        _url.search = new URLSearchParams(param);
        return fetch(_url, {
            method: "GET",
            headers: self.getHeaders(headers)
        }).then(self.onResponse)
        .catch(error => {
            if (error.status == 403) {
                return self.refreshToken(retry)
                .then(authRes => self.get(url, param, headers, ++retry));
            } else {
                throw error;
            }
        });
    }

    post(url, param = {}, headers = {}, retry = 0) {
        const self = this;
        const _url = new URL(url, self.getBaseUrl(url));
        return fetch(_url, {
            method: "POST",
            headers: self.getHeaders(headers),
            body: JSON.stringify(param)
        }).then(self.onResponse)
        .catch(error => {
            if (error.status == 403) {
                return self.refreshToken(retry)
                .then(authRes => self.post(url, param, headers, ++retry));
            } else {
                throw error;
            }
        });
    }

    onResponse(res) {
        if (res.status != 200) {
            throw res; // 에러는 밖에서 처리
        }

        return res.text()
        .then(str => {
            try {
                return JSON.parse(str); // isJSON
            } catch (error) {
                return str; // isNotJSON
            }
        })
    }

    refreshToken(retry) {
        if (retry > 3) {
            console.error(`refreshToken retry :: ${retry}`);
            window.location.href = "#login";
        }
        return AuthService.getInstance().onRefresh()
        .catch(authError => {
            window.location.href = "#login";
        });
    }
}