import $ from "jquery";
import axios from "axios";

$(function() {
	axios({
		method: "GET",
		url: "http://localhost:53000/course",
		params: {
			id: 1000
		}
	}).then(function(result) {
		console.log(result);
		let course = result.data;
		console.log(course);
		for (let c of course) {
			console.log(c)
		}
	}).catch(function(reason) {
		console.log(reason);
	})
});
