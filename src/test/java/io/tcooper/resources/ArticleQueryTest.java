package io.tcooper.resources;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.mongodb.client.MongoCollection;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import io.tcooper.api.Article.ArticleResponse;
import io.tcooper.core.Article;
import io.tcooper.core.ArticleUid;
import io.tcooper.data.MongoFindIterableHelper;
import javax.ws.rs.core.Response;
import org.bson.conversions.Bson;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DropwizardExtensionsSupport.class)
public class ArticleQueryTest {

  private final MongoCollection<Article> mongoCollectionMock = mock(MongoCollection.class);

  private final ResourceExtension underTest = ResourceExtension.builder()
      .addResource(new ArticleQuery(mongoCollectionMock))
      .build();

  private final ArticleUid articleUid = new ArticleUid();
  private final String name = "Test Article";
  private final String description = "Test description";
  private final String content = "Some test content";
  private final int page = 1;

  private MongoFindIterableHelper<Article> mongoFindIterableHelper;

  private ArticleResponse testArticle;
  private Article article;

  @BeforeEach
  void setup() {
    testArticle = new ArticleResponse(articleUid.toString(), name, description, page, content);
    article = new Article(articleUid, name, description, content, page);
    mongoFindIterableHelper = new MongoFindIterableHelper<>(article);
  }

  @Test
  void givenIQueryForAValidArticleThenArticleFound() {
    when(mongoCollectionMock.find(isA(Bson.class))).thenReturn(mongoFindIterableHelper);

    Response response = underTest.target("/article/942ee57d-7a4f-43fc-91f9-b474d8972ae0")
        .request()
        .buildGet()
        .invoke();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK_200);

    ArticleResponse articleResponse = response.readEntity(ArticleResponse.class);

    assertThat(articleResponse.getArticleId()).isEqualTo(articleUid.toString());
    assertThat(articleResponse.getName()).isEqualTo(name);
    assertThat(articleResponse.getDescription()).isEqualTo(description);
    assertThat(articleResponse.getContent()).isEqualTo(content);
    assertThat(articleResponse.getPage()).isEqualTo(page);
  }


}
