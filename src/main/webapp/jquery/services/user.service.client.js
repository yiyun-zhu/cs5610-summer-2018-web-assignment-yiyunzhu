function UserServiceClient() {
	this.createUser = createUser;
	this.findAllUsers = findAllUsers;
	this.findUserById = findUserById;
	this.deleteUser = deleteUser;
	this.updateUser = updateUser;

   	this.url = 'http://localhost:8080/api/user';
   	this.reg = 'http://localhost:8080/api/register';
   	this.logi = 'http://localhost:8080/api/login';
   	this.pro = 'http://localhost:8080/api/profile'
   	this.logo = 'http://localhost:8080/api/logout';
   	var self = this;


   function updateUser(userId, user) {
		return fetch(self.url + '/' + userId, {
			method: 'put',
			body: JSON.stringify(user),
			headers: {
				'content-type': 'application/json'
			}
		})

   }
   
   function findUserById(userId) {
	   	return fetch(self.url + '/' + userId) 
			.then(function (response) {
				return response.json();
	    	});
   }

   function deleteUser(userId) {
   		return fetch(self.url + '/' + userId, {
   			method: 'delete'
		});
   }

   function findAllUsers() {
	   	return fetch(self.url)
			.then(function (response) {
				return response.json();
		   });
   }

   function createUser(user) {
		return fetch(self.url, {
			method: 'post',
			body: JSON.stringify(user),
			headers: {
				'content-type': 'application/json'
			}
		});
   }

}
