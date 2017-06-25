package mvp.wyyne.douban.moviedouban.api;

import io.reactivex.Observable;
import mvp.wyyne.douban.moviedouban.api.bean.WelfarePhotoList;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by XXW on 2017/6/25.
 */

public interface IWelfareApi {
    @GET("/api/data/福利/50/{page}")
    Observable<WelfarePhotoList> getWelfarePhoto(@Path("page") int page);
}
