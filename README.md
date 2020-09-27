# content-store

This application is a basic back end content store for a blog built with Dropwizard. Why dropwizard? 
I had enough configuring Jetty when building [uri-friend](https://github.com/tcooper-uk/uri-friend).

Content store has one goal, could you guess? Store some content... It should probably serve it up as well, 
who knows maybe we might throw some search in the mix.

How to start the content-store application
---

You should check your database is running... Then:

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/content-store-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

Other Userful Endpoints
---

TBC...

Database
---

Prerequisites
- Docker
- Mongo shell

#### Running

```
$ cd scripts
$ ./devMongo.sh start
$ mongo -u root -p admin --authenticationDatabase admin content setupDatabase.js
```
#### Stop
```
$ cd scripts
$ ./devMongo.sh stop
```
