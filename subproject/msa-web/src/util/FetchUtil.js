export const BASE_URL = API_URL;
export const AUTH_URL = AUTL_URL;

export default class FetchUtil {
    
    static #defaultHeaders = {
        "Content-Type": "application/json; charset=UTF-8"
    };

    static getBaseUrl(url) {
        return url.startsWith("http") ? "" : BASE_URL;
    }

    static getBaseUrl(headers) {
        return Object.assign({}, FetchUtil.#defaultHeaders, headers);
    }

    static get(url, param = {}, headers = {}) {
        const _url = new URL(url, this.getBaseUrl(url));
        _url.search = new URLSearchParams(param);
        return fetch(_url, {
            method: "GET",
            headers: this.getHeaders(headers)
        }).then(res => res.text())
        .then(str => {
            try {
                return JSON.parse(str); // isJSON?
            } catch (error) {
                return str;
            }
        });
    }

    static post(url, param = {}, headers = {}) {
        const _url = new URL(url, this.getBaseUrl(url));
        return fetch(_url, {
            method: "POST",
            headers: this.getHeaders(headers),
            body: JSON.stringify(param)
        }).then(res => {
            if (res.status != 200) {
                throw res; // 에러는 밖에서 처리해라
            }
            return res;
        }).then(res => res.text())
        .then(str => {
            try {
                return JSON.parse(str); // isJSON?
            } catch (error) {
                return str;
            }
        });
    }
}