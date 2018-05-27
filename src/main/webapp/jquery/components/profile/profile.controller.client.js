//IIFE
(function () {

	var $usernameFld, $phoneFld, $emailFld;
	var $roleFld, $DoBFld;
	var phone, email, role, DoB;
	var $updateBtn, $logoutBtn;
	var userService = new UserServiceClient();
	
	$(main);

	function main() {
		$usernameFld = $('#usernameFld');
		$phoneFld = $('#phoneFld');
		$emailFld = $('#emailFld');
		$roleFld = $('#roleFld');
		$DoBFld = $('#DoBFld');
		$updateBtn = $('#updateBtn').click(updateProfile);
		$logoutBtn = $('#logoutBtn').click(logout)

		getProfile();

	}

	function getProfile() {
		userService.getProfile().then(renderUser);
	}

	function renderUser(response) {
		console.log(response);
		if (response !== null) {
			$usernameFld.val(response.username);
			$phoneFld.val(response.phone);
			$emailFld.val(response.email);
			$roleFld.val(response.role);
			$DoBFld.val(response.dateOfBirth);
		}	
	}

	function updateProfile() {
		phone = $phoneFld.val();
		email = $emailFld.val();
		role = $roleFld.val();
		DoB = $DoBFld.val();
		
		var user = {
			phone: phone,
			email: email,
			role: role,
			dateOfBirth: DoB
		}

		userService.updateProfile(user).then(success);
		
	}

	function success(response) {
		console.log(response);
		if (response !== null) {
			alert('successfully updated!');
		}
	}

	function logout() {
		userService.logout();
		// clear the form after logout
		$usernameFld.val('');
		$phoneFld.val('');
		$emailFld.val('');
		$roleFld.val('');
		$DoBFld.val('');
		window.location.href = '../login/login.template.client.html';
	}
	
})();