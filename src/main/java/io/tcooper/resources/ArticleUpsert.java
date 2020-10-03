package io.tcooper.resources;

import com.codahale.metrics.annotation.Timed;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import io.tcooper.api.Article.ArticleInsertRequest;
import io.tcooper.core.Article;
import io.tcooper.core.ArticleUid;
import io.tcooper.core.mappers.ArticleApiResponseMapper;
import io.tcooper.core.mappers.ArticleInsertRequestMapper;
import io.tcooper.core.mappers.ArticleInsertResponseMapper;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Deals with all upsert requests for articles
 */
@Path("/article")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleUpsert {

  private final Logger LOGGER = LoggerFactory.getLogger(ArticleInsertRequest.class);
  private final MongoCollection<Article> articleCollection;

  public ArticleUpsert(MongoCollection<Article> articleCollection) {
    this.articleCollection = articleCollection;
  }

  @PUT
  @Timed
  public Response createArticle(@Valid ArticleInsertRequest articleInsertRequest) {
    Article article = ArticleInsertRequestMapper.map(articleInsertRequest);
    articleCollection.insertOne(article);
    return Response.accepted(ArticleInsertResponseMapper.map(article, true)).build();
  }

  @POST
  @Path("/{articleUid}")
  public Response updateArticle(@PathParam("articleUid") ArticleUid articleUid, @Valid ArticleInsertRequest articleInsertRequest) {
    Article article = ArticleInsertRequestMapper.map(articleUid, articleInsertRequest);

    Article newArticle = articleCollection
        .findOneAndReplace(Filters.eq("articleUid", articleUid), article);

    return newArticle == null
        ? Response.status(HttpStatus.NOT_FOUND_404).build()
        : Response.accepted(ArticleInsertResponseMapper.map(article, false)).build();
  }
}
