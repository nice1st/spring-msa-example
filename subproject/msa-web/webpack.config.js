const webpack = require("webpack")
const path = require("path")

module.exports = {
    mode: "development",
    entry: {
        module: "./src/*",
    },
    output: {
        paht: './dist',
        filename: '[name].js',
        publicPath: '/'
    },
    plugins: [
        new webpack.DefinePlugin({
            AUTH_URL: JSON.stringify(process.env.AUTH_URL) || "http://localhost:18095",
            BASE_URL: JSON.stringify(process.env.BASE_URL) || "http://localhost:18090"
        }),
    ],
}