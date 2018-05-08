package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.widget.TextView;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.City;

/**
 * @author XXW
 * @date 2017/10/19
 */

public class CityAdapter extends BaseRvAdapter<City> {

    public CityAdapter(Context context, List<City> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_city_name;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        TextView tvCityName = holder.getView(R.id.tv_city_name);
        tvCityName.setText(mList.get(position).getCityName());
    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
