import AuthService from '../service/AuthService';

export const BASE_URL = process.env.BASE_URL;
export const AUTH_URL = process.env.AUTH_URL;
export let TOKEN = undefined;

export default class FetchUtil {
    
    static set token(token) {
        TOKEN = token;
    }

    static getBaseUrl(url) {
        return url.startsWith("http") ? "" : BASE_URL;
    }

    static getHeaders(headers) {
        const defaultHeaders = {
            "Content-Type": "application/json; charset=UTF-8",
            "Authorization": TOKEN,
        };
        return Object.assign({}, defaultHeaders, headers);
    }

    static get(url, param = {}, headers = {}, retry = 0) {
        const _url = new URL(url, this.getBaseUrl(url));
        _url.search = new URLSearchParams(param);
        return fetch(_url, {
            method: "GET",
            headers: this.getHeaders(headers)
        }).then(FetchUtil.onResponse)
        .catch(error => {
            if (error.status == 403) {
                return FetchUtil.refreshToken(retry)
                .then(authRes => FetchUtil.get(url, param, headers, ++retry));
            } else {
                throw error;
            }
        });
    }

    static post(url, param = {}, headers = {}, retry = 0) {
        const _url = new URL(url, this.getBaseUrl(url));
        return fetch(_url, {
            method: "POST",
            headers: this.getHeaders(headers),
            body: JSON.stringify(param)
        }).then(FetchUtil.onResponse)
        .catch(error => {
            if (error.status == 403) {
                return FetchUtil.refreshToken(retry)
                .then(authRes => FetchUtil.post(url, param, headers, ++retry));
            } else {
                throw error;
            }
        });
    }

    static onResponse(res) {
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

    static refreshToken(retry) {
        if (retry > 3) {
            console.error(`refreshToken retry :: ${retry}`);
            window.location.href = "/login";
        }
        return AuthService.getInstance().onRefresh()
        .catch(authError => {
            window.location.href = "/login";
        });
    }
}