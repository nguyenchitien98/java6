app = angular.module("admin-app",["ngRoute"]);

app.config(function ($routeProvider){
	$routeProvider
	.when("/product",{
		templateUrl: "/assets/admin/product/index.html",
		controller:"product-ctrl"
	})
	.when("/authorize",{
		templateUrl: "/assets/admin/authority/index.html",
		controller:"authority-ctrl"
	})
	.when("/unauthorized",{
		templateUrl: "/assets/admin/authority/unauthorized.html",
		controller:"authority-ctrl"
	})
	.when("/user",{
		templateUrl: "/assets/admin/user/index.html",
		controller: "user-ctrl"
	})
	.otherwise({
		template: "<h1 class='text-center'>Welcome to Administration</h1>"
	})
})