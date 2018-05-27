//IIFE
(function () {

	// jQuery(main);

	var $usernameFld, $passwordFld;
	var $firstNameFld, $lastNameFld, $roleFld;
	var $searchBtn, $createBtn, $updateBtn;
	var $deleteBtn, $editBtn;
	var $tbody, $userRowTemplate;
	var $inputRow;
	var userService = new UserServiceClient();
	$(main);

	function main() {
		
		$usernameFld = $('#usernameFld'); 
		$passwordFld = $('#passwordFld'); 
		$firstNameFld = $('#firstNameFld'); 
		$lastNameFld = $('#lastNameFld');  
		$roleFld = $('#roleFld');

		$tbody = $('tbody');
		$userRowTemplate = $('.template');
		$inputRow = $('.inputRow');

		// create click
		$searchBtn = $('.wbdv-search');
		$createBtn = $('.wbdv-create').click(createUser);
		$updateBtn = $('.wbdv-update').click(updateUser);

		// fetch the users data from server
		$tbody.empty();
		findAllUsers();

	}

	function findAllUsers() {
		userService
				.findAllUsers()
				.then(renderUsers);
	}

	function createUser() {
		var user = new User($usernameFld.val(),
					$passwordFld.val(),
					$firstNameFld.val(),
					$lastNameFld.val(),
					$roleFld.val());
		var username = $usernameFld.val();
		userService
				.createUser(user)
				.then(findAllUsers);	
	}

	function updateUser(event) {
		var user = new User($usernameFld.val(),
					$passwordFld.val(),
					$firstNameFld.val(),
					$lastNameFld.val(),
					$roleFld.val());
	
		var userId = $inputRow.attr('id');
		userService
				.updateUser(userId, user)
				.then(findAllUsers);
	}

	function deleteUser(event) {
		var deleteBtn = $(event.currentTarget);
		var userId = deleteBtn.parent().parent().parent().attr('id');
		userService
				.deleteUser(userId)
				.then(findAllUsers);
	}

	function findUserById(event) {
		var editBtn = $(event.currentTarget);
		var userId = editBtn.parent().parent().parent().attr('id');
		$inputRow.attr('id', userId);
		userService
				.findUserById(userId)
				.then(renderUser);
	}

	function renderUser(user) {
		$usernameFld.val(user.username)
		$passwordFld.val(user.password); 
		$firstNameFld.val(user.firstName); 
		$lastNameFld.val(user.lastName); 
		$roleFld.val(user.role);
	}

	function renderUsers(users) {
		$tbody.empty();
		for (var i = 0; i < users.length; i++) {
			var user =  users[i];
			var clone = $userRowTemplate.clone();
			clone.find('.username').html(user.username);
			clone.find('.firstName').html(user.firstName);
			clone.find('.lastName').html(user.lastName);
			clone.find('.role').html(user.role);
			clone.attr('id', user.id);
			clone.find('.wbdv-delete').click(deleteUser);
			clone.find('.wbdv-edit').click(findUserById);
			$tbody.append(clone);
		}
	}
	
        

})();