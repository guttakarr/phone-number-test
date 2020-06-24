(function(){
    angular.module('app').controller('HomeController', HomeController);

    function HomeController($scope,$http){
        var vm = $scope;

        vm.phoneNumber= null;
        vm.errorMsg = '';
        vm.hasError = false;
        vm.hasResults = false;

        var paginationOptions = {
            pageNumber: 1,
            pageSize: 10
        };

        $scope.gridOptions = {
            enableSorting : false,
            paginationPageSizes: [10],
            paginationPageSize: 10,
            useExternalPagination: true,
            columnDefs: [
                {name: 'phoneNumber'}
            ],
            onRegisterApi: function(gridApi) {
                $scope.gridApi = gridApi;
                gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
                    paginationOptions.pageNumber = newPage;
                    paginationOptions.pageSize = pageSize;
                    getPage();
                });
            }
        };

        var getPage = function() {
            var req = {
                pageNumber : paginationOptions.pageNumber,
                pageSize : paginationOptions.pageSize,
                phoneNumber : vm.phoneNumber
            };
            $http({
                method : 'POST',
                url : '/phone',
                headers: {
                    'Content-Type': 'application/json'
                },
                data: req
            }).then(function(resp){
                console.log(resp);
                vm.hasResults = true;
                vm.gridOptions.data = resp.data.phoneNumbers;
                vm.gridOptions.totalItems = resp.data.total;

            })
        };

        function validate(){
            var isValid =  (vm.phoneNumber.length === 7 || vm.phoneNumber.length === 10);
            var r = new RegExp(/^[0-9]/ig);
            isValid = isValid && r.test(vm.phoneNumber);

            return isValid;

        }

        vm.submit = function(){

            if(!validate()){
                vm.hasError = true;
                vm.errorMsg = 'Invalid Input';

            }else {
                paginationOptions.pageNumber =1 ;
                getPage();
            }
        };

        function reset(){
            vm.errorMsg = '';
            vm.hasError = false;
        }
    }
})();