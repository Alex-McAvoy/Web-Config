const {
	resolve
} = require("path");

const HtmlWebpackPlugin = require("html-webpack-plugin")
const MiniCssExtractPlugin = require("mini-css-extract-plugin");
const CssMinimizerPlugin = require("css-minimizer-webpack-plugin");
const EslintWebpackPlugin = require("eslint-webpack-plugin");


//设置NodeJS环境变量，默认是生产环境
// process.env.NODE_ENV = "development";

//复用loader
const commonCssLoader = [
	//取代style-loader，提取js中的css成单独文件
	MiniCssExtractPlugin.loader,
	//将css文件整合到js中
	"css-loader",
	/*css兼容性处理
		需要在package.json中配置browserslist
		"browserslist": {
			"development": [
				"last 1 chrome version",
				"last 1 firefox version",
				"last 1 safari version"
			],
			"production": [
				">0.1%",
				"not dead",
				"not op_mini all"
			]
		}
	*/
	{
		loader: 'postcss-loader',
		options: {
			postcssOptions: {
				plugins: [
					'postcss-preset-env'
				]
			}
		}
	}
];

module.exports = {
	//单入口
	// entry: resolve(__dirname, "src/entry.js"),
	//多入口：有几个入口输出几个bundle，格式为：name: 入口文件
	entry: {
		main1: resolve(__dirname, "src/entry.js"),
		main2: resolve(__dirname, "src/entry1.js"),
	},
	//输出
	output: {
		//输出文件名
		filename: "js/[name].[contenthash:10].min.js",
		//输出路径
		path: resolve(__dirname, "build")
	},
	//loader
	module: {
		rules: [{
			oneOf: [{
				//匹配css文件
				test: /\.css$/,
				use: [...commonCssLoader]
			}, {
				//匹配less文件
				test: /\.less$/,
				use: [...commonCssLoader, "less-loader"]
			}, {
				//匹配图片资源
				test: /\.(png|jpg|jpeg|gif|svg)$/,
				//内置asset模块代替原来的url-loader
				type: "asset",
				parser: {
					dataUrlCondition: {
						/*  图片base64处理
					    图片大小小于8kb，就会被base64处理
				        优点：减少请求数量，减缓服务器压力
					    缺点：图片体积会更大，文件请求速度变慢
					*/
						maxSize: 8 * 1024
					}
				},
				//命名格式
				generator: {
					filename: "img/[name].[hash:6].[ext]"
				}
			}, {
				//处理字体资源
				test: /\.(ttf|eot|woff2)$/,
				//内置asset模块代替原来的file-loader
				type: "asset/resource",
				generator: {
					filename: "font/[name].[hash:6].[ext]"
				}
			}, {
				//js兼容性处理
				test: /\.m?js$/,
				use: {
					loader: 'babel-loader',
					options: {
						presets: ["@babel/preset-env"],
						plugins: ["@babel/plugin-transform-runtime"],
						/* 利用babel开启文件资源缓存
						   1. 方式：在打包后的js、css文件输出名中，加上hash值，以防止文件改动后仍从缓存中读取
						   2. 三种解决方案
						   - [hash]：每次webpack构建时会生成一个唯一的hash，可能会导致所有缓存失效
						   - [chunkhash]：根据chunk生成hash，若打包来源同一chunk则hash值相同，由于css是被js引入的，两者同属一个chunk，hash相同
						   - [contenthash]： 根据文件内容生成hash
						*/
						cacheDirectory: true
					},
				},
				exclude: /node_modules/
			}]
		}]
	},
	//插件
	plugins: [
		//复制html文件，并自动引入打包输出后的所有资源
		new HtmlWebpackPlugin({
			template: resolve(__dirname, "src/index.html")
		}),
		//将js中的css拆分为文件
		new MiniCssExtractPlugin({
			// 对输出的css文件进行重命名
			filename: 'css/built.[contenthash:10].min.css'
		}),
		//压缩css
		new CssMinimizerPlugin(),
		/*  对js进行语法检查
		    采用airbnb规则，需在package.json中设置eslintConifg
			"eslintConfig": {
				"extends": "airbnb-base"
			}
		 */
		new EslintWebpackPlugin({
			//检查的文件类型
			extensions: "js",
			//排除第三方库
			exclude: "/node_modules/",
			//开启自动修复
			fix: true
		})
	],
	/* 代码分割
	   1. 将js中使用的node_modules中代码单独打包成一个chunk输出
	   2. 自动分析多入口文件中是否有公共依赖，若有，则提取出来
	*/
	optimization: {
		splitChunks: {
			chunks: "all"
		}
	},
	/* 生产模式
	   1. 自动压缩js、html
	   2. ES6 模块下自动启动Tree shaking，即去重无用代码
	    - 可在package.json 设置 sideEffects: false，即所有代码都没有副作用，均可进行Tree shaking
		- 问题：可能会把 css、jpg 等文件去除
		- 解决：设置 sideEffects: ["*.css"]，则不会对css文件进行处理
	*/
	mode: "production",
	//source-map
	devtool: "hidden-source-map",
	//忽略的包，需要在src的html中，引入jquery
	externals: {
		jquery: "jQuery"
	}
}
