function initPage() {
console.log(window.location.pathname);
	if (window.location.pathname == "/laptopshop/shopadmin/poverzicht.html"){
		loadProductsAdmin();
	}
	if (window.location.pathname == "/laptopshop/shopadmin/ptoevoegen.html"){
		loadAtttribues();
	}


}


function loadProductsAdmin(){
	 fetch("/restservices/countries")
	    .then(response => response.json())
	    .then(j => {
	    	console.log(j);
	    	});
}

//function loadAtttribues(){
//	fetch('/laptopshop/shopadmin/loadattr')
//    .then(response => response.json())
//    .then(j => {
//        document.querySelectorAll('.ip-data li').forEach(function(el) {
//        	  
//            var node = document.createElement("DIV");
//            var textnode = document.createTextNode(j[el.id]);
//            node.appendChild(textnode);
//            el.appendChild(node);  
//        })
//       long = j['longitude'];
//       lat = j['latitude'];
//      city = j['city'];
//      weatherData(lat, long, city);
//        
//    })
//	
//}
	







document.addEventListener('DOMContentLoaded', initPage);




