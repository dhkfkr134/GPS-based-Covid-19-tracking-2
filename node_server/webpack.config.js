const NodemonPlugin = require('nodemon-webpack-plugin')
const path = require("path");
const NodePolyfillPlugin = require("node-polyfill-webpack-plugin")

const MODE = process.env.WEBPACK_ENV;
const ENTRY_FILE = path.resolve(__dirname,"src", "index.js");

const OUTPUT_DIR = path.join(__dirname, ".build");

const config = {
    entry : ["@babel/polyfill", ENTRY_FILE], //entry 는 파일이 들어오는곳
    mode: MODE,
    target: 'node',
    module : {
        rules: [
            {
                test :  /\.(js)$/,
                exclude: /node_modules/,
                use : [
                    {
                        loader : 'babel-loader' //js es6 문법을 구식으로 바꿔줌
                    }
                ]
            },
        ]
    },
    plugins: [
        new NodemonPlugin({
            script: OUTPUT_DIR + '/main.js',
            wathch: OUTPUT_DIR,
        }),
       	new NodePolyfillPlugin()
    ],
    output : { //output 은 파일이 나가는곳 이다. output 은 객체여야 한다
        path : OUTPUT_DIR,
        filename : "[name].js" //[name].[format]" 파일의 이름과 파일의 확장자,형식 이다.
    },
    resolve: {
        modules: ['node_modules'],
        extensions: ['.js', '.json', '.ts']
    }
}; 

module.exports = config;