package io.tcooper.resources;

import com.codahale.metrics.annotation.Timed;
import io.tcooper.api.Saying;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorld {

  private final String template;
  private final String defaultName;
  private final AtomicLong counter = new AtomicLong();

  public HelloWorld(String template, String defaultName) {
    this.template = template;
    this.defaultName = defaultName;
  }

  @GET
  @Timed
  public Saying sayHello(@QueryParam("name")Optional<String> name) {
    final String value = String.format(template, name.orElse(defaultName));
    return new Saying(counter.incrementAndGet(), value);
  }
}
