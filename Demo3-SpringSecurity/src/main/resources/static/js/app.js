/**
 *  = {} objet
 *  = [] tableau
 */
var app = angular.module("myApp", []); 

app.controller("IndexController", function($scope, $http) {
	// TODO remplacer par des routes
	
	$scope.menu=["Inscription", "Listes", "Utilisateurs", "Logout"];
	$scope.selectedMenu=null;
	
	// in html : <a ng-click='doSelect(m)'>{{m}}</a>
	$scope.doSelect = function (val) {
		$scope.selectedMenu = val;
	}
});

app.controller("ListEtudiantController", function($scope, $http) {	
    $scope.pageEtudiant = [];
    $scope.pageSize = 3;
    $scope.pageCourante = 0;
    
    $scope.listEtudiants = function() {
        $http({
                method: 'GET',
                url: "/etudiants" + "?" + "&page=" + $scope.pageCourante + "&size=" + $scope.pageSize
            })
            .success(function(data) {
                $scope.pageEtudiant = data;
                $scope.pages = new Array(data.totalPages)
            });
    };
    // pagination
    $scope.goToPage = function(p) {
        $scope.pageCourante = p;
        $scope.listEtudiants();
    }    
    // appel fonction au démarage
    $scope.listEtudiants();    
});



app.controller("InscriptionController", function($scope, $http) {	
	$scope.etudiant = {};
	$scope.errors = null;
	$scope.mode = {value:"form"};
    $scope.exception = { message:null };
    
//    $scope.errorMessage = null;
    
    $scope.saveEtudiant= function() {
    	$http.post("etudiants", $scope.etudiant)
//        $http({
//                method: 'POST',
//                url: "/etudiants",
//                data: $scope.etudiant,
//            })
            .success(function(data) {
            	console.log(data);
            	if(!data.errors) {
            		$scope.etudiant=data;
            		$scope.errors=null;
            		$scope.mode.value="confirm"
            	} else {
            		$scope.errors=data;
            	}
            })
            .error(function(data) {
            	$scope.exception.message = data.message;
            	console.log($scope.exception.message);
            })
            ;
    };
});
