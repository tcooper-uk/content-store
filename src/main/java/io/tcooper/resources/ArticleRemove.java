package io.tcooper.resources;

import com.codahale.metrics.annotation.Timed;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import io.tcooper.core.Article;
import io.tcooper.core.ArticleUid;
import io.tcooper.core.mappers.ArticleApiResponseMapper;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;

@Path("/article")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleRemove {

  private final MongoCollection<Article> articleCollection;

  public ArticleRemove(MongoCollection<Article> articleCollection) {
    this.articleCollection = articleCollection;
  }

  @DELETE
  @Path("/{articleUid}")
  @Timed
  public Response deleteArticle(@PathParam("articleUid") ArticleUid articleUid) {
    Article article = articleCollection
        .findOneAndDelete(Filters.eq("articleUid", articleUid));
    
    return article == null
        ? Response.status(HttpStatus.NOT_FOUND_404).build()
        : Response.ok(ArticleApiResponseMapper.map(article)).build();
  }

}
