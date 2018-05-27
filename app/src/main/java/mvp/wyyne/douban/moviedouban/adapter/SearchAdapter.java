package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

/**
 * 搜索结果
 *
 * @author Wynne
 * @date 2018/5/27
 */

public class SearchAdapter extends BaseRvAdapter<Subjects> {

    public SearchAdapter(Context context, List<Subjects> data) {
        super(context, data);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_search;
    }

    @Override
    public void bindView(BaseItemViewHolder holder, int position) {
        TextView tvName = holder.getView(R.id.tv_name);
        ImageView ivAvatar = holder.getView(R.id.iv_avatar);
        TextView tvContent = holder.getView(R.id.tv_content);

        Subjects subjects = mList.get(position);

        Glide.with(mContext).load(subjects.getImages().getSmall()).into(ivAvatar);
        tvName.setText(subjects.getTitle());
        String average =
                subjects.getRating().getAverage() > 0 ? subjects.getRating().getAverage() + "" : "5.5";

        String date;
        if (subjects.getPubdates() != null) {
            date = subjects.getPubdates().get(0).length() > 0 ? subjects.getPubdates().get(0) : "2017-10-10(中国大陆)";
        } else {
            date = "2017-10-10(中国大陆)";
        }
        String country = date.substring(date.indexOf("("), date.indexOf(")") + 1);
        String content = average + "/" + date + "/" + country;
        tvContent.setText(content);


    }

    @Override
    public void bindHeadView(BaseItemViewHolder holder, int position) {

    }

    @Override
    public void bindFooterView(BaseItemViewHolder holder, int position) {

    }
}
