# Zainabed Spring Security JWT

[![Build Status](https://dev.azure.com/zainabed/spring-security-jwt/_apis/build/status/zainabed.spring-security-jwt)](https://dev.azure.com/zainabed/spring-security-jwt/_build/latest?definitionId=1)

Security JWT makes it easy to configure authentication and authorization security system into Spring Boot applications. It secures application with few configurations.  

Our objectives are
  - Application specific authentication
  - Decople authentication & authorization
  - Configurable JWT token based security

### Concept

##### Authentication

Spring Security Jwt uses `Basic Authentication` schema to validate user.

Basic authentication is a simple authentication scheme built into the HTTP protocol. The client sends HTTP requests with the Authorization header that contains user credentials.
Authorization header is constructed using string `username:password` encoded in `Base64` and prefixed with String `Basic`

```
Authorization: Basic dGVzdDp0ZXN0
```

##### Authorization

Once the user is logged in, Spring Security JWT creates JWT token as HTTP response to client. 

Response example
```
{
    token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
    type: Bearer
    refereshToken: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
}
```
Then each subsequent request will have to include the JWT token, allowing the user to access resources that are permitted with that token. 

Whenever the user wants to access a protected resource, the client should send the JWT token in the `Authorization` header using the `Bearer` schema. The content of the header should look like the following:
```
Authorization: Bearer <token>
```



### Installation

Use your favorite Maven-compatible build tool to pull the dependencies from Maven Central

#### Maven

```xml
<dependency>
  <groupId>com.zainabed.spring</groupId>
  <artifactId>zainabed-spring-security-jwt</artifactId>
  <version>1.0.0</version>
</dependency>
```
#### Configuration
First step is to enable JWT security by extending `JwtWebSecuriy` class and annotation it with `@EnableJwtSecurity`.


```java
    import com.zainabed.spring.security.jwt.annotation.EnableJwtSecurity;
    import com.zainabed.spring.security.jwt.security.JwtWebSecuriy;

    @EnableJwtSecurity
    public class ApplicationWebSecurity extends JwtWebSecuriy{
    }
```

Second step is to set JWT properties in `application.properties` file.

```javascript
jwt.token.secret= <secret value>
jwt.token.expiration= <expiration time in seconds>
```

This is common configuration to enable both authentication and authorization.



##### Authentication
To activate authentication define JWT authentication property and set value as true.

```javascript
jwt.authentication=true
```

Authentication is mapped at `"/auth"` route. To generate JWT token HTTP POST request should call "/auth" request with Basic Authentication header which should include user credentails which should be encoded with Base64


```
Authorization: Basic <username-value:password-value>
```
```
Authorization: Basic QWxhZGRpbjpPcGVuU2VzYW1l
```

Security authentication controller let you to define your own authentication module to verify user credential. to do so you have to implement `JwtAuthenticationService` and annotate it with `@Service`.

```java
@Service
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {

	@Override
	public UserDetail authenticate(UserCredential userCredential) throws AuthenticationException {
		// Define your own authentication mechanism and return result as UserDetail object
	}

}
```

##### Authorization
Authorization process get activated when you define token secret and expiration time in properties file and extend `JwtWebSecuriy` , you can secure you REST controller as

```java
@RestController
@RequestMapping(value = "/test")
public class TestControlller {

	@Secured("ROLE_USER")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String testUserWithRole() {
		return "Test user with User role.";
	}

	@Secured(value = "ROLE_ADMIN")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String testAdmin() {
		return "Test user with Admin role.";
	}
}

```