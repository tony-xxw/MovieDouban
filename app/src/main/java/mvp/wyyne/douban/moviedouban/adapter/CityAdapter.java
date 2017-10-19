package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.City;

/**
 * Created by XXW on 2017/10/19.
 */

public class CityAdapter extends BaseRvAdapter<City> {

    public CityAdapter(Context context, List<City> data) {
        super(context, data);
    }

    @Override
    int getLayoutId() {
        return R.layout.item_city_name;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {

    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
