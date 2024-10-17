# caf-util-spring

This project provides Spring utility code.

## caf-propertyresolver-spring

This module provides Spring applications with the ability to resolve secret properties in Spring configuration files.

Assuming you have a secret key named `MY_PASSWORD`, you can reference it in a Spring configuration file like this:

```yaml
password: ${secret:MY_PASSWORD:password}
```

The `SecretPropertyEnvironmentListener` class will resolve the secret key and replace it with the actual value, or use a default value 
if the secret key is not found. In the example above, the default value is `password`.

The `SecretPropertyEnvironmentListener` class can be added to a Spring application like this:

```java
public static void main(String[] args) {
    final SpringApplication application = new SpringApplication(MyService.class);
    application.addListeners(new SecretPropertyEnvironmentListener());
    application.run(args);
}
```