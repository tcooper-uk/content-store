package io.tcooper.resources;

import com.codahale.metrics.annotation.Timed;
import io.tcooper.api.Article.Article;
import io.tcooper.api.Article.ArticleInsertRequest;
import io.tcooper.api.Article.ArticleResponse;
import io.tcooper.api.Article.ArticleUid;
import java.time.ZonedDateTime;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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

  @PUT
  @Timed
  public ArticleResponse createArticle(@Valid ArticleInsertRequest articleInsertRequest) {

    LOGGER.info("Found article:");
    LOGGER.info(articleInsertRequest.getName());
    LOGGER.info(articleInsertRequest.getDescription());
    LOGGER.info(String.valueOf(articleInsertRequest.getPage()));
    LOGGER.info(articleInsertRequest.getContent());

    Article article = new Article(new ArticleUid(),
        articleInsertRequest.getName(),
        articleInsertRequest.getDescription(),
        articleInsertRequest.getContent(),
        articleInsertRequest.getPage());

    return new ArticleResponse(article, false, ZonedDateTime.now());
  }

}