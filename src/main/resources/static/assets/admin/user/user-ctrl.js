app.controller("user-ctrl",function($scope,$http){
	$scope.users=[];
	$scope.form={};
	
	$scope.sortColumn='fullname';
	$scope.searchName=''; // set default search/filter
	
	$scope.initialize = function(){
		// load user
		$http.get("/rest/accounts/list").then(resp =>{
			$scope.users= resp.data;
		})
	}
	
	// Khởi đầu
	$scope.initialize();
	
	// Xóa form
	$scope.reset = function(){
		$scope.form={
			photo: 'johndoe.png',
		};
	}
	
	// hiển thị lên form
	$scope.edit= function(user){
		$scope.form = angular.copy(user);
	
		$(".nav-tabs a:eq(0)").tab("show")
	}
	
	// Thêm user mới
	$scope.create = function(){
		var user = angular.copy($scope.form);
		$http.post(`/rest/accounts`,user).then(resp=>{
			$scope.users.push(resp.data);
			$scope.reset();
			alert("Thêm user mới thành công!");
		}).catch(error => {
			alert("Lỗi thêm user");
			console.log("Error",error);
		});
	}
	
	// Cập nhật user
	$scope.update = function(){
		var user = angular.copy($scope.form);
		$http.put(`/rest/accounts/${user.username}`,user).then(resp =>{
			var index= $scope.users.findIndex(u => u.username == user.username);
			$scope.users[index] = user;
			alert("Cập nhật user thành công!");
		}).catch(error =>{
			alert("Lỗi cập nhật user");
			console.log("Error",error);
		})
	}
	
	// Xóa user
	$scope.delete= function(user){
		$http.delete(`/rest/accounts/${user.username}`).then(resp =>{
			var index = $scope.users.findIndex(u => u.username==user.username);
			$scope.users.splice(index,1);
			$scope.reset();
			alert("Xóa user thành công!");
		}).catch(error =>{
			alert("Lỗi xóa user");
			console.log("Error",error);
		})
	}
	
	//upload hình
	$scope.imageChangede = function(files){
		var data = new FormData();
		data.append('file',files[0]);
		$http.post('/rest/upload/images',data,{
			transformRequest: angular.identity,
			headers: {'Content-Type': undefined}
		}).then(resp =>{
			$scope.form.photo= resp.data.name;
		}).catch(error =>{
			alert("Lỗi upload hình ảnh");
			console.log("error",error);
		})
	}
	
	$scope.pager={
		page: 0,
		size: 10,
		get users(){
		var start = this.page * this.size;
		return $scope.users.slice(start,start+this.size);
		},
		
		get count(){
			return Math.ceil(1.0 * $scope.users.length / this.size);
		},
		first(){
			this.page=0;
		},
		prev(){
			this.page--;
			if(this.page<0){
				this.last();
			}
		},
		
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		
		last(){
			this.page=this.count-1;
		}
		
	}
	
})






