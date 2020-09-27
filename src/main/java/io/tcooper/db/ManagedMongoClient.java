package io.tcooper.db;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.dropwizard.lifecycle.Managed;

public class ManagedMongoClient implements Managed {

  private final DatabaseConfiguration databaseConfiguration;

  private MongoClient mongoClient;

  /**
   * Create a managed mongo client bound to the dropwizard lifecycle
   * @param databaseConfiguration
   */
  public ManagedMongoClient(DatabaseConfiguration databaseConfiguration) {
    this.databaseConfiguration = databaseConfiguration;
    mongoClient = createMongoClient(databaseConfiguration);
  }


  @Override
  public void start() throws Exception {
    // we have already created our client
  }

  @Override
  public void stop() throws Exception {
    // Ensure client closed on shutdown
    mongoClient.close();
  }

  public MongoClient getMongoClient() {
    return mongoClient;
  }

  private static MongoClient createMongoClient( DatabaseConfiguration databaseConfiguration) {
    MongoCredential mongoCredential = MongoCredential.createScramSha1Credential(
        databaseConfiguration.getUsername(),
        databaseConfiguration.getName(),
        databaseConfiguration.getPassword().toCharArray());

    return MongoClients.create(
        MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(databaseConfiguration.getUri()))
            .credential(mongoCredential)
            .build()
    );
  }
}
