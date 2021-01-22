const webpack = require("webpack")
const path = require("path")

module.exports = {
    entry: {
        app: path.resolve(__dirname + "/src/index.js"),
        service: path.resolve(__dirname + "/src/service/index.js"),
        router: path.resolve(__dirname + "/src/router/router.js"),
    },
    output: {
        path: path.resolve(__dirname + "/dist"),
        filename: '[name].js',
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: [[
                            '@babel/env', {
                                useBuiltIns: 'usage',
                                corejs: 3
                            }
                        ]],
                        plugins: [[
                            '@babel/plugin-proposal-class-properties', {
                                loose:true
                            }
                        ]]
                    }
                }
            },
            {
                test: /\.html$/,
                exclude: /(node_modules)/,
                loader: 'html-loader'
            },
        ],
    }
}