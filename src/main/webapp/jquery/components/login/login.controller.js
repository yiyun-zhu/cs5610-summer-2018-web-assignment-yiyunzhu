//IIFE
(function () {

	var $usernameFld, $passwordFld;
	var $loginBtn;
	var userService = new UserServiceClient();
	$(main);

	function main() {
		$usernameFld = $('#usernameFld'); 
		$passwordFld = $('#passwordFld'); 

		$loginBtn = $('.btn').click(login);

	}

	function login() {
		var username = $usernameFld.val();
		var password = $passwordFld.val();

		var user = new User(username, password);
		userService.login(user).then(success);
		
	}

	function success(response) {
		var res = 1;
		response.json()
			.catch(function(err){
				res=0;				
			}).then(function(){
				if(res==0){
					alert("username or password not correct!");
	  	    	}
	    		else{
					alert("logged in!");
					// window.location.href = '../profile/profile.template.client.html';			
				}
			});
	}
	
})();