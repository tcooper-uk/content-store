package io.tcooper.resources;

import com.codahale.metrics.annotation.Timed;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import io.tcooper.api.Article.ArticleCollection;
import io.tcooper.api.Article.ArticleResponse;
import io.tcooper.core.Article;
import io.tcooper.core.ArticleUid;
import io.tcooper.core.mappers.ArticleApiResponseMapper;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;

@Path("/article")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleQuery {

  private final MongoCollection<Article> articleCollection;

  public ArticleQuery(MongoCollection<Article> articleCollection) {
    this.articleCollection = articleCollection;
  }

  @GET
  @Path("/{articleUid}")
  @Timed
  public Response getArticleById(@PathParam("articleUid") ArticleUid articleUid) {
    ArticleResponse articleResponse = articleCollection
        .find(Filters.eq("articleUid", articleUid))
        .map(ArticleApiResponseMapper::map)
        .first();

    return articleResponse == null
        ? Response.status(HttpStatus.NOT_FOUND_404).build()
        : Response.ok(articleResponse).build();
  }

  @GET
  @Path("/top/{limit}")
  @Timed
  public Response getArticles(@PathParam("limit") int limit) {
    ArrayList<ArticleResponse> articleResponses = articleCollection.find()
        .limit(limit)
        .map(ArticleApiResponseMapper::map)
        .into(new ArrayList<>());

    return Response.ok(new ArticleCollection(articleResponses.size(), articleResponses)).build();
  }

}
