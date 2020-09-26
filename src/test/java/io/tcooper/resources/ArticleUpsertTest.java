package io.tcooper.resources;

import static org.assertj.core.api.Assertions.assertThat;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import io.tcooper.api.Article.Article;
import io.tcooper.api.Article.ArticleInsertRequest;
import io.tcooper.api.Article.ArticleResponse;
import java.util.Set;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ArticleUpsertTest {

  private final ResourceExtension underTest = ResourceExtension.builder()
      .addResource(new ArticleUpsert())
      .build();

  private final String name = "Test Article";
  private final String description = "Test description";
  private final String content = "Some test content";
  private final int page = 1;

  private ArticleInsertRequest articleInsertRequest;

  @BeforeEach
  void setup() {
    articleInsertRequest = new ArticleInsertRequest(name, description, content, page);
  }

  @Test
  void givenValidArticleThenValidResponse(){
    Response response = underTest.target("/article")
        .request()
        .buildPut(Entity.entity(articleInsertRequest, MediaType.APPLICATION_JSON))
        .invoke();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED_202);

    ArticleResponse articleResponse = response.readEntity(ArticleResponse.class);
    Article article = articleResponse.getArticle();

    assertThat(articleResponse.isCreated()).isFalse();
    assertThat(article.getName()).isEqualTo(name);
    assertThat(article.getDescription()).isEqualTo(description);
    assertThat(article.getContent()).isEqualTo(content);
    assertThat(article.getPage()).isEqualTo(page);
    assertThat(article.getArticleUid()).isNotNull();
  }

  @Test
  void givenInvalidArticleThenValidErrors(){
    Response response = underTest.target("/article")
        .request()
        .buildPut(Entity.entity(new ArticleInsertRequest(), MediaType.APPLICATION_JSON))
        .invoke();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY_422);

    Errors validationErrors = response.readEntity(Errors.class);
    assertThat(validationErrors.errors.length).isEqualTo(4);

    Set<String> validationErrorSet = Set.of(validationErrors.errors);
    assertThat(validationErrorSet).contains("name must not be empty");
    assertThat(validationErrorSet).contains("content must not be empty");
    assertThat(validationErrorSet).contains("page must be greater than or equal to 1");
    assertThat(validationErrorSet).contains("description must not be empty");
  }



  final static class Errors {

    String[] errors;

    public Errors() {
    }

    public String[] getErrors() {
      return errors;
    }

    public void setErrors(String[] errors) {
      this.errors = errors;
    }
  }
}