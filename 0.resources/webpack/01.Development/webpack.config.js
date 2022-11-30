/* 
	npx webpack-dev-server
*/
const {
	resolve
} = require("path");

const HtmlWebpackPlugin = require("html-webpack-plugin")

//设置NodeJS环境变量，默认是生产环境
// process.env.NODE_ENV = "development";

module.exports = {
	//入口
	entry: resolve(__dirname, "src/entry.js"),
	//输出
	output: {
		//输出文件名
		filename: "js/main.js",
		//输出路径
		path: resolve(__dirname, "build")
	},
	//loader
	module: {
		rules: [{
			//以下loader只会匹配一次，应注意不能有两个loader处理同一类型的文件
			oneOf: [{
				//匹配css文件
				test: /\.css$/,
				use: [
					//创建style标签，将js中的样式资源插入html中
					"style-loader",
					//将css文件变为commonjs模块加载到js中，内容为样式字符串
					"css-loader"
				]
			}, {
				//匹配less文件
				test: /\.less$/,
				use: [
					"style-loader",
					"css-loader",
					//将less文件变为css
					"less-loader"
				]
			}, {
				//匹配图片资源
				test: /\.(png|jpg|jpeg|gif|svg)$/,
				//内置asset模块代替原来的url-loader
				type: "asset",
				parser: {
					dataUrlCondition: {
						//图片大小小于8kb，就会被base64处理
						//优点：减少请求数量，减缓服务器压力
						//缺点：图片体积会更大，文件请求速度变慢
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
			}]
		}]
	},
	//插件
	plugins: [
		//复制html文件，并自动引入打包输出后的所有资源
		new HtmlWebpackPlugin({
			template: resolve(__dirname, "src/index.html")
		})
	],
	//开发模式
	mode: "development",

	/*
		开发服务器：自动编译、自动打开浏览器、自动刷新浏览器
		特点：只会在内存中编译打包，不会有任何输出
		启动指令：npx webpack-dev-server
	*/
	devServer: {
		//构建后路径
		static: resolve(__dirname, 'build'),
		port: 3000,
		//启动gzip压缩
		compress: true,
		//自动打开浏览器
		open: true,
		/* 热部署 HMR
		   1. css的HMR功能由css-loader内部实现
		   2. js与html默认不使用HMR功能，
		     - html不需要使用
			 - js需要添加支持HMR功能的代码
		   
		*/
		hot: true
	},
	/* source-map：提供源代码到构建后代码的映射技术
	   1. 作用：当构建后的代码运行时出错，可通过映射追踪到源代码
	   2. 参数：[inline-|hidden-|eval-][nosources-][cheap-[module-]]source-map
	     - source-map：外部，错误代码准确信息、源代码错误位置
		 - inline-source-map：内联，错误代码准确信息、源代码错误位置，只生成一个内联source-map，速度比外部快
		 - eval-source-map：内联，错误代码准确信息、源代码错误位置，每个文件都生成一个source-map
		 - hidden-source-map：外部，错误代码错误原因，构建后代码的错误位置，能够隐藏源代码信息，外部生成映射文件，速度比内联慢
		 - nosources-source-map：外部，错误代码准确信息，能够隐藏源代码和构建后代码信息
		 - cheap-source-map：外部，错误代码准确信息、源码的错误位置，只精确到行
		 - cheap-module-source-map：外部，错误代码准确信息、源码的错误位置，只精确到行，会将loader的source-map加入
	   3. 速度对比：eval > inline > cheap
	   4. 调试友好对比：source-map > cheap-module-source-map > cheap-source-map
	   5. 内联：代码体积变大，生产环境不考虑
	   6. 代码隐藏：hidden 只隐藏源代码、nosources 隐藏所有代码
	   7. 环境使用
	     - 开发环境：速度快，调试更友好，一般选用 eval-source-map 或 eval-cheap-module-source-map
	     - 生产环境：速度快，考虑源代码是否需要隐藏，一般选用 hidden-source-map 或 nosource-source-map
	*/
	devtool: "source-map"
}
