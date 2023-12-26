/**
 * 
 */

let el = document.getElementById("number");
let del = document.getElementById("dash");
let add = document.getElementById("plus");

del.addEventListener("click",function() {
	if(el.value > parseInt(el.min) ) {
		el.value = parseInt(el.value) - 1;
	}
});

add.addEventListener("click",function() {
	if(el.value < parseInt(el.max)) {
		el.value = parseInt(el.value) + 1;
	}
});