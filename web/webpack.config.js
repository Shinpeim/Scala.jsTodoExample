const webpack = require('webpack');
const path = require('path');

const env = process.env.NODE_ENV || 'development';

module.exports = {
    entry: "./src/index.js",

    output: {
        filename: "bundle.js",
        path: __dirname + "/dist"
    },

    devtool: "source-map",

    resolve: {
        extensions: [".js"],
        alias: {
            vue: 'vue/dist/vue.js'
        },
        modules: [
            "node_modules"
        ],
    },

    module: {
        loaders: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loader: 'babel-loader',
                query:{
                   compact: false
                }
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader'
            },
            {
                test: /\.css$/,
                loader: "style-loader!css-loader"
            }
        ]
    }
};
