function initPage() {
console.log(window.location.pathname);
	if (window.location.pathname.endsWith("/shopadmin/poverzicht.html")){
		loadProductsAdmin();

		
		
	}
	if (window.location.pathname.endsWith("/shopadmin/ptoevoegen.html")){
		loadAtttribues();
		
		// Create formulier knop afhandeling
		document.querySelector("#new-product").addEventListener("click", function(){
			var formData = new FormData(document.querySelector("#nForm"));
			var encData = new URLSearchParams(formData.entries());
			var fetchoptions = {
					method: 'POST',
					body: encData,
					headers: {
						'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
					}
			}
			
			fetch("../restservices/producten/new", fetchoptions)
				.then(function(response) {
				if (response.ok){
					alert("Product is succesvol toegevoegd!");
				} else{
					alert("Er is een fout opgetreden");
				}
				})
		})
	}
	
	if (window.location.pathname.endsWith("/shopadmin/pupdate.html")){
		loadAtttribues(update = true);
		
		document.querySelector("#update-product").addEventListener("click", function(){
			var code = document.querySelector("#update-product").getAttribute('data-code');
			console.log(code);
			var formData = new FormData(document.querySelector("#nForm"));
			var encData = new URLSearchParams(formData.entries());
			var fetchoptions = {
					method: 'PUT',
					body: encData
//					headers: {
//						'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
//					}
			}
			
			fetch("../restservices/producten/" + code, fetchoptions)
			.then(function(response) {
				if (response.ok){
					alert("Product is succesvol bijgewerkt!");
				}
				else if (response.status == 409)
					alert("Product is niet gevonden");
				else
					alert("Product is niet bijgewerkt");
			})
			
			
		})
	}
	


}

// laadt de producten voor de admin overicht pagina
function loadProductsAdmin(){
	 fetch("../restservices/producten/overzicht")
	    .then(response => response.json())
	    .then(j => {
	    	for (var i in j){
	    		console.log(j);
	    		var row = document.createElement("TR");
	    		row.setAttribute("class", "rij");
	    		for ( let b in j[i]) {
	    			row.setAttribute('data-code', j[i]['id']);
//	    			console.log(j[i]);
	    			 var td = document.createElement("TD");
	    			 var textnode = document.createTextNode(j[i][b]);
	    			 td.appendChild(textnode);
	    			 row.appendChild(td);
				}
	    		
	    		
// delete knop
	    		var del = document.createElement("INPUT");
	    		
	    		del.setAttribute("type", "button");
	    		del.setAttribute("class", " button delete");
	    		del.setAttribute("value", "Verwijder");
	    		del.setAttribute("data-code", j[i]['id']);

    			row.appendChild(del);
    			
    			var wijzig = document.createElement("INPUT");
	    		
    			wijzig.setAttribute("type", "button");
    			wijzig.setAttribute("class", "button wijzig");
    			wijzig.setAttribute("value", "Wijzig");
    			wijzig.setAttribute("data-code", j[i]['id']);

    			row.appendChild(wijzig);
    			
    		
	    		document.querySelector('.shopadmin .middle-content table tbody').appendChild(row);	    	

	    	} 
	    	
	 
		window.onload=function(){
			 document.querySelectorAll(".delete")
			 	.forEach(btn => {
			 		btn.addEventListener('click', e => {
			 			e.stopPropagation();
					let code = e.target.dataset.code
					console.log(e.target.dataset.code);
					var fetchoptions = {
							method: 'DELETE',
							headers: {
								'Authorization': 'Bearer ' + window.sessionStorage.getItem("sessionToken")
							}
					}
					
					fetch("../restservices/producten/" + code, fetchoptions)
					.then(function(response) {
						if (response.ok){
							console.log("delete");
							
						}
						else if (response.status == 404)
							console.log("niet gevonden");
						else
							console.log("niet verwijderen");
					})
					
					
					
					
			 		})
			 		
					
				});
			 
			 
			 
			 
// wijzig
			 
			 document.querySelectorAll(".wijzig")
			 	.forEach(btn => {
			 		btn.addEventListener('click', e => {
			 			e.stopPropagation();
					let code = e.target.dataset.code
					console.log(e);
					console.log(e['path'][1]);
					localStorage.setItem("product_id", e.target.dataset.code);
					window.location.href = "../shopadmin/pupdate.html";
					
			 		})
			 		
					
				});
			 
			 
			 
			}
	    });
	 
	 
	 
	 
}


// laad product attributen bij het aanmaken van een product
function loadAtttribues(update){
	var uproduct; 	
	var setOption;
	if(update == true){
		fetch('../restservices/producten/' + localStorage.getItem('product_id'))
	    .then(response => response.json())
	    .then(j => {
	    	uproduct = j;
	    	console.log(j);
	    	
	    	document.querySelector('#model').value = j['model'];
	    	document.querySelector('#prijs').value = j['prijs'];
	    	document.querySelector('#resolutie').value = j['resolutie'];
	    	document.querySelector('#werkgeheugen').value = j['werkgeheugen'];
	    	});
	    }
	
	
	fetch('../restservices/producten/laadattr')
    .then(response => response.json())
    .then(j => {
    	
    	
    	var ul = document.createElement("UL");
    	var li = document.createElement("LI");
    	for (var i in j){
    		var select = document.createElement("SELECT");
    		select.setAttribute("id", i);
    		select.setAttribute("name", i);

    		console.log(i);
    		
    	if(update == true){
    		if (i == "Gkaart"){
    			setOption = uproduct['gid'];
    		}
    		if (i == "Leverancier"){
    			setOption = uproduct['lid'];
    		}
    		if (i == "Processor"){
    			setOption = uproduct['pid'];
    		}
    		if (i == "Opslag"){
    			setOption = uproduct['oid'];
    		}
    		
    		document.querySelector('#update-product').setAttribute("data-code", uproduct['id']);
    		
    		
    	}
    		
    		for ( let b in j[i]) {
    			var option = document.createElement("OPTION");
    			
    			for ( let c in j[i][b]) {
    				
    				
    				
    				if (c == 'naam'){

    					var textnode = document.createTextNode(j[i][b]['naam']);
            			option.appendChild(textnode);
            			option.setAttribute("value", j[i][b]['id']);
            			if (setOption == j[i][b]['id']){
            				option.setAttribute("selected", "selected");
            			}
            			
    				} 
    				if (c == 'naamE'){
    					var textnode = document.createTextNode(j[i][b]['naamE'] + " " + j[i][b]['geheugen'] + "GB");
            			option.appendChild(textnode);
            			option.setAttribute("value", j[i][b]['id']);
            			if (setOption == j[i][b]['id']){
            				option.setAttribute("selected", "selected");
            			}
            			
    				} 
    				
    			}
    			
    			select.appendChild(option);
    		}
    		
    		
    		document.querySelector('.ptoevoegen').appendChild(select);
    		
    	}
        
    })
	
}
	






document.addEventListener('DOMContentLoaded', initPage);