module.exports = function (env = {}) {
    return require(`./webpack.${env.NODE_ENV || "development"}.js`);
};