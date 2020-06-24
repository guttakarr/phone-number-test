(function(){
    angular.module('app')
        .config(function($routeProvider){
           var basePath = 'app/';
            $routeProvider
                .when('/Home',{
                    templateUrl: basePath+'home/home.html',
                    controller : 'HomeController'
                }).
                otherwise({
                    redirectTo :'/Home'
            });



        });

})();