Authentication and Authorization Process:
-----------------------------------------
1. User trying to access a secure page for the first time
2. Behind the scenes few filters like AuthorizationFilter, DefaultLoginPageGeneratingFilter identify that the user is not logged in and redirect the user to login page.
3. User entered his credentials and the request is intercepted by filters.
4. Filters like UsernamePasswordAuthenticationFilter extracts the username, password from the request and form an object of UsernamePasswordAuthenticationToken which is an implementation of Authentication interface. With the object created it invokes authenticate() method of ProviderManager.
5. ProviderManager which is an implementation of AuthenticationManager identify the list of Authentication providers available that are supporting given authentication object style. In this scenario, the authenticate() method of our custom AuthenticationProvider will be invoked by ProviderManager.
6. AuthenticationProvider (EasyMoneyUsernamePasswordAuthenticationProvider) load the user details from DB. Once the user details loaded, it takes help from the BcryptPasswordEncoder to compare the password and validate if the user is authentic or not.
7. At last it returns the Authentication object with the details of authentication success or not to ProviderManager
8. ProviderManager checks if authentication is successful or not. If not, it will try with other available AuthenticationProviders. Otherwise, it simply returns the authentication details to the filters.
9. The Authentication object is stored in SecurityContext object by the filter for future use and the response will be returned to the end user.