//IIFE
(function () {

	var $usernameFld, $passwordFld, $verifyPasswordFld;
	var $registerBtn;
	var userService = new UserServiceClient();
	$(main);

	function main() {
		$usernameFld = $('#usernameFld'); 
		$passwordFld = $('#passwordFld'); 
		$verifyPasswordFld = $('#verifyPasswordFld'); 
		$registerBtn = $('.btn').click(register);

	}

	function register() {
		var username = $usernameFld.val();
		var password = $passwordFld.val();
		var verifyPassword = $verifyPasswordFld.val();
		
		if (password !== verifyPassword) {
			alert('password not consistent'); 
			return;		
		}

		var user = new User(username, password);
		userService.register(user).then(success);
		
	}

	function success(response) {
		if (response===null) {
			alert("can't register!");
		} else {
			alert("successfully registed!");
			window.location.href = '../profile/profile.template.client.html';
		} 
	}
	
})();