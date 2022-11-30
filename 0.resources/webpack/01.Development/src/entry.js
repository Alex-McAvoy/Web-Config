/**
 *  1. npm install --save-dev
 *  2. 生产环境 webpack ./src/index.js -o ./build --mode=production
 *  3. 开发环境 webpack ./src/index.js -o ./build --mode=development
 * **/

//引入js
import "./js/request.js";

//引入 json
import data from "./json/test.json";
console.log(data);

//引入css样式及其使用的图片
// import "./test.css";
import "./less/test.less";

//引入字体图标样式
import "./font/iconfont.css";

import print from "./js/print.js";

/* 开启 HMR 功能
  1. module.hot为true，说明开启HMR功能
  2. 方法会监听相应js文件的变化
  3. 一旦发生变化，其他模块不会重新打包构建，会执行回调函数
*/
if(module.hot) {
	
	module.hot.accept("./js/request.js", function(){
		print();
	})
}