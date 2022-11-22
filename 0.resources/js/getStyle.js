/*
 * 获取指定元素的当前的样式
 * 参数：
 * 		obj 要获取样式的元素
 * 		name 要获取的样式名
 */
function getStyle(obj, name) {

	if (window.getComputedStyle) {
		//正常浏览器方式，具有getComputedStyle()方法
		return getComputedStyle(obj, null)[name];
	} else {
		//IE8方式，没有getComputedStyle()方法
		return obj.currentStyle[name];
	}
}
