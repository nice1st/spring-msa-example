const path = require("path");
const {merge} = require('webpack-merge');
const common = require('./webpack.common.js');
const dotenv = require("dotenv-webpack");

// webpack 공통 설정 merge
module.exports = merge(common, {
    mode: 'production',

    plugins: [
        new dotenv({
            path: path.resolve(__dirname, "./env/prod.env"),
        }),
    ],
});