package io.tcooper;

import com.mongodb.client.MongoCollection;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.tcooper.core.Article;
import io.tcooper.db.DatabaseConfiguration;
import io.tcooper.db.ManagedMongoClient;
import io.tcooper.health.PersistenceHealthCheck;
import io.tcooper.health.TemplateHealthCheck;
import io.tcooper.resources.ArticleUpsert;
import io.tcooper.resources.HelloWorld;
import org.bson.UuidRepresentation;
import org.mongojack.JacksonMongoCollection;

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

        // Setup database connection
        DatabaseConfiguration databaseConfiguration = contentStoreConfiguration.getDatabase();
        ManagedMongoClient mongoClient = new ManagedMongoClient(databaseConfiguration);

        // create collection
        MongoCollection<Article> articleCollection = JacksonMongoCollection
            .builder()
            .build(mongoClient.getMongoClient(),
                databaseConfiguration.getName(),
                "article",
                Article.class,
                UuidRepresentation.STANDARD)
            .getMongoCollection();

        final PersistenceHealthCheck persistenceHealthCheck = new PersistenceHealthCheck(articleCollection);

        environment.healthChecks().register("template", templateHealthCheck);
        environment.healthChecks().register("persistence", persistenceHealthCheck);
        environment.jersey().register(helloWorld);
        environment.jersey().register(new ArticleUpsert(articleCollection));
    }

    @Override
    public void initialize(Bootstrap<ContentStoreConfiguration> bootstrap) {
        // init some stuff
    }


}
