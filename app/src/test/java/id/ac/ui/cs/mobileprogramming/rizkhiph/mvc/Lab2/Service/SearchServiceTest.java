package id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.Service;

import org.junit.Test;

import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.activity.SearchControllerListener;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.common.RequestApi;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.constant.ApiConstants;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.controller.SearchController;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.service.SearchService;
import id.ac.ui.cs.mobileprogramming.rizkhiph.mvc.Lab2.view.SearchView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SearchServiceTest {
    RequestApi requestApi = mock(RequestApi.class);
    SearchService searchService = new SearchService(mock(SearchControllerListener.class)) {
        public RequestApi getRequestApi() {
            return requestApi;
        }
    };

    @Test
    public void searchAnime() {
        this.searchService.searchAnime("One Piece");
        verify(requestApi, times(1)).getRequest(
                ApiConstants.SEARCH_ANIME_API + "?q=" + "One Piece", searchService);
    }

    @Test
    public void searchManga() {
        this.searchService.searchManga("One Piece");
        verify(requestApi, times(1)).getRequest(
                ApiConstants.SEARCH_MANGA_API + "?q=" + "One Piece", searchService);
    }

    @Test
    public void responseToJsonStringifySimple() {
        assertThat(this.searchService.formatString("{}"), equalTo("\n{\n\t\n}"));
    }

    @Test
    public void responseToJsonStringifyComplex() {
        assertThat(this.searchService.formatString("{\"name\":\"Forsen\", \"email\": \"SebastianEli@example.com\"}"),
                equalTo("\n{\n\t\"name\":\"Forsen\",\n\t \"email\": \"SebastianEli@example.com\"\n}"));
    }

    @Test
    public void onGetResponseFromRequestSetText() {
        this.searchService.setResponseText("One Piece");
        verify(this.searchService.getListener(), times(1)).onGetResponse(
                this.searchService.formatString("One Piece"));
    }

    @Test
    public void getObjectRequestApi() {
        SearchService searchService = new SearchService(mock(SearchControllerListener.class));
        searchService.getRequestApi();
    }
}
