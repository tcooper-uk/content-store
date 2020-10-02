package io.tcooper.resources;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.mongodb.client.MongoCollection;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import io.tcooper.api.Article.ArticleInsertRequest;
import io.tcooper.api.Article.ArticleInsertResponse;
import io.tcooper.core.Article;
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

  private final MongoCollection<Article> mongoCollectionMock = mock(MongoCollection.class);

  private final ResourceExtension underTest = ResourceExtension.builder()
      .addResource(new ArticleUpsert(mongoCollectionMock))
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

    ArticleInsertResponse articleInsertResponse = response.readEntity(ArticleInsertResponse.class);
    Article article = articleInsertResponse.getArticle();

    assertThat(articleInsertResponse.isCreated()).isFalse();
    assertThat(article.getName()).isEqualTo(name);
    assertThat(article.getDescription()).isEqualTo(description);
    assertThat(article.getContent()).isEqualTo(content);
    assertThat(article.getPage()).isEqualTo(page);
    assertThat(article.getArticleUid()).isNotNull();

    verify(mongoCollectionMock, times(1)).insertOne(isA(Article.class));
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

    verifyNoInteractions(mongoCollectionMock);
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
