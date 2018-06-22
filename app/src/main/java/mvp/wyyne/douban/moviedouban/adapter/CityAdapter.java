package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;

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
    public void bindView(BaseItemViewHolder holder, final int position) {
        holder.setText(R.id.tv_city_name, mList.get(position).getCityName());
        holder.getView(R.id.ll_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onItemClick(position, "");
            }
        });
    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
