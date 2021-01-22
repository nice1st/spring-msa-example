const webpack = require("webpack")
const path = require("path")

module.exports = {
    entry: {
        service: path.resolve(__dirname + "/src/service/index.js"),
    },
    output: {
        path: path.resolve(__dirname + "/dist"),
        filename: '[name].js',
        library: "service",
        libraryTarget: "umd",
    },
    module: {
        rules: [{
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
        }],
    }
}