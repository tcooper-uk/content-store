package io.tcooper;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.tcooper.health.TemplateHealthCheck;
import io.tcooper.resources.HelloWorld;

public class ContentStoreApplication extends Application<ContentStoreConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ContentStoreApplication().run(args);
    }

    @Override
    public String getName() {
        return "content-store";
    }

    @Override
    public void run(ContentStoreConfiguration contentStoreConfiguration, Environment environment)
        throws Exception {
        final HelloWorld helloWorld = new HelloWorld(
            contentStoreConfiguration.getTemplate(),
            contentStoreConfiguration.getDefaultName()
        );

        final TemplateHealthCheck templateHealthCheck = new TemplateHealthCheck(
            contentStoreConfiguration.getTemplate()
        );

        environment.healthChecks().register("template", templateHealthCheck);
        environment.jersey().register(helloWorld);
    }

    @Override
    public void initialize(Bootstrap<ContentStoreConfiguration> bootstrap) {
        // init some stuff
    }
}
