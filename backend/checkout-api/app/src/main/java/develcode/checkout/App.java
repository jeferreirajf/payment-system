/*
 * This source file was generated by the Gradle 'init' task
 */
package develcode.checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

import develcode.checkout.infra.configurations.WebserverConfiguration;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "test");
        SpringApplication.run(WebserverConfiguration.class, args);
    }
}
