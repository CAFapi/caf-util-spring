# caf-util-spring

This project provides Spring utility code.

## caf-propertysource-spring

This module provides a custom Spring Boot property source that allows for integration of secrets into your application's configuration.

### Usage

1. Add the `caf-propertysource-spring` dependency to your project:
```xml
<dependency>
    <groupId>com.github.cafapi.util.spring</groupId>
    <artifactId>caf-propertysource-spring</artifactId>
    <version>VERSION</version>
</dependency>
```

2. Register the `CafConfigEnvironmentListener`:
```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApplication.class);
        app.addListeners(new CafConfigEnvironmentListener()); // Register the listener
        app.run(args);
    }
}
```

3. In your `application.properties` or `application.yaml`, you can now use the `secret:` prefix to reference secrets:

```yaml
my.secret.property: secret:MY_SECRET_KEY
my.secret.with.default: secret:ANOTHER_SECRET:defaultValue
```

The secrets will be automatically resolved during application startup.
