function initPage() {
console.log(window.location.pathname);
	if (window.location.pathname.endsWith("/shopadmin/poverzicht.html")){
		logOut();
		var strJ = localStorage.getItem("sessionTokenStr");
		var parseJson = JSON.parse(strJ);
		if(strJ != null){
			if (parseJson['role'] != "admin"){
				window.location.href = "../shopadmin/";
			}else if (parseJson['role'] == "admin"){
				loadProductsAdmin();
			}
		}else{
			window.location.href = "../shopadmin/";
		}
		
		
		

		
		
	}
	if (window.location.pathname.endsWith("/shopadmin/ptoevoegen.html")){
		logOut();
		var strJ = localStorage.getItem("sessionTokenStr");
		var parseJson = JSON.parse(strJ);
		if(strJ != null){
			if (parseJson['role'] != "admin"){
				window.location.href = "../shopadmin/";
			}else if (parseJson['role'] == "admin"){
				loadAtttribues();
			}
		}else{
			window.location.href = "../shopadmin/";
		}
		// Create formulier knop afhandeling
		document.querySelector("#new-product").addEventListener("click", function(){
			var formData = new FormData(document.querySelector("#nForm"));
			var encData = new URLSearchParams(formData.entries());
			var fetchoptions = {
					method: 'POST',
					body: encData,
					headers: {
						'Authorization': 'Bearer ' + window.localStorage.getItem("sessionToken")
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
		logOut();
		var strJ = localStorage.getItem("sessionTokenStr");
		var parseJson = JSON.parse(strJ);
		if(strJ != null){
			if (parseJson['role'] != "admin"){
				window.location.href = "../shopadmin/";
			}else if (parseJson['role'] == "admin"){
				loadAtttribues(update = true);
			}
		}else{
			window.location.href = "../shopadmin/";
		}
		
		
		
		document.querySelector("#update-product").addEventListener("click", function(){
			var code = document.querySelector("#update-product").getAttribute('data-code');
			var formData = new FormData(document.querySelector("#nForm"));
			var encData = new URLSearchParams(formData.entries());
			var fetchoptions = {
					method: 'PUT',
					body: encData,
					 headers: {
					 'Authorization': 'Bearer ' + window.localStorage.getItem("sessionToken")
					 }
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
	
	if (window.location.pathname.endsWith("/shopadmin/")){
	
		document.querySelector("#login").addEventListener("click", function(){
			var formData = new FormData(document.querySelector("#loginform"));
			var encData = new URLSearchParams(formData.entries());
			fetch('../restservices/authentication', {method: 'POST', body: encData})
			.then(function(response){
				if(response.ok){
					return response.json();
				} else{alert("fout gebruiksnaam of wachtwoord")};
			})
			.then(function(token){
					var base64Url = token['JWT'].split('.')[1];
				    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
				    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
				        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
				    }).join(''));

								
				window.localStorage.setItem("sessionTokenStr", jsonPayload)
				window.localStorage.setItem("sessionToken", token.JWT)
			})
			.then(function(){
				window.location.href = "../shopadmin/poverzicht.html";
			})
			.catch(error => console.log(error));
			
			
		})

		
		
	}
	


}

// laadt de producten voor de admin overicht pagina
function loadProductsAdmin(){
	
	 fetch("../restservices/producten/overzicht")
	    .then(function(response){
	    	if (response.status != 200){
	    		alert("oops, er ging iets fout /n probeer opnieuw in te loggen");
	    		window.location.href = "../shopadmin/";
	    	}else{
	    		return response.json();
	    	}
	    })
	    .then(j => {
	    	
	    	for (var i in j){
	    		var row = document.createElement("TR");
	    		row.setAttribute("class", "rij");
	    		for ( let b in j[i]) {
	    			row.setAttribute('data-code', j[i]['id']);

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
	    	
	 
		
	    }).then(function(){
				 document.querySelectorAll(".delete")
				 	.forEach(btn => {
				 		btn.addEventListener('click', e => {
				 			e.stopPropagation();
						let code = e.target.dataset.code
						var fetchoptions = {
								method: 'DELETE',
								headers: {
									'Authorization': 'Bearer ' + window.localStorage.getItem("sessionToken")
								}
						}
						
						fetch("../restservices/producten/" + code, fetchoptions)
						.then(function(response) {
							if (response.ok){
								
								e.path[1].style.display = 'none';
							
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
						let code = e.target.dataset.code;
						localStorage.setItem("product_id", e.target.dataset.code);
						window.location.href = "../shopadmin/pupdate.html";
						
				 		})
				 		
						
					});
				 
				 
				 
	    	
	    	
	    	
	    });
	 
	 
	 
	 
}


// laad product attributen bij het aanmaken van een product
function loadAtttribues(update){
	var uproduct; 	
	var setOption;
	var fetchoptions = {
			method: 'GET',
			headers: {
				'Authorization': 'Bearer ' + localStorage.getItem("sessionToken")
			}
	}
	
	
	if(update == true){
		fetch('../restservices/producten/' + localStorage.getItem('product_id'), fetchoptions)
	    .then(response => response.json())
	    .then(j => {
	    	uproduct = j;
	    	
	    	document.querySelector('#model').value = j['model'];
	    	document.querySelector('#prijs').value = j['prijs'];
	    	document.querySelector('#resolutie').value = j['resolutie'];
	    	document.querySelector('#werkgeheugen').value = j['werkgeheugen'];
	    	});
	    }
	setTimeout(function(){
	
	fetch('../restservices/producten/laadattr',fetchoptions)
    .then(response => response.json())
    .then(j => {
    	
    	
    	var ul = document.createElement("UL");
    	var li = document.createElement("LI");
    	for (var i in j){
    		var select = document.createElement("SELECT");
    		select.setAttribute("id", i);
    		select.setAttribute("name", i);

    		
    		
    	if(update == true){
    		if (i == "Gkaart"){
    			setOption = uproduct['gid'];
    		}
    		if (i == "Processor"){
    			setOption = uproduct['pid'];
    		}
    		if (i == "Opslag"){
    			setOption = uproduct['oid'];
    		}
    		if (i == "Leverancier"){
    			setOption = uproduct['lid'];
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
    
	}, 50);
	
}


function logOut(){
	document.querySelectorAll(".login-out")
	.forEach(btn => {
 		btn.addEventListener('click', e => {
 			e.stopPropagation();
 			 localStorage.clear();
 			 window.location.href = "../shopadmin/";
		
 		})
 		
		
	});
	
}





document.addEventListener('DOMContentLoaded', initPage);