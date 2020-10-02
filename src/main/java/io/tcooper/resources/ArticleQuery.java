package io.tcooper.resources;

import com.mongodb.client.MongoCollection;
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

@Path("/article")
@Produces(MediaType.APPLICATION_JSON)
public class ArticleQuery {

  private final MongoCollection<Article> articleCollection;

  public ArticleQuery(MongoCollection<Article> articleCollection) {
    this.articleCollection = articleCollection;
  }

  @GET
  @Path("/{articleUid}")
  public ArticleResponse getArticleById(@PathParam("articleUid") ArticleUid articleUid) {
    ArticleResponse article = articleCollection
        .find(Filters.eq("articleUid", articleUid))
        .map(ArticleApiResponseMapper::map)
        .first();

    return article;
  }

  @GET
  @Path("/top")
  public ArticleCollection getArticles() {
    ArrayList<ArticleResponse> articleResponses = articleCollection.find()
        .limit(10)
        .map(ArticleApiResponseMapper::map)
        .into(new ArrayList<>());

    return new ArticleCollection(articleResponses.size(), articleResponses);
  }

}
