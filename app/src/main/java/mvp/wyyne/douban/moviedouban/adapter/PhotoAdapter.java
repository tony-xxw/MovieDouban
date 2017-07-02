package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.security.PublicKey;
import java.util.List;

import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Photos;
import mvp.wyyne.douban.moviedouban.api.bean.Trailers;

/**
 * Created by XXW on 2017/6/24.
 */

public class PhotoAdapter extends BaseRvAdapter<Photos> implements View.OnClickListener {
    public static final String TAG = "Content";
    public static final String HEAD = "head";
    public static final String FOOTER = "footer";
    private int stills_count;
    private LinearLayout mContent;
    private LinearLayout mHeadView;
    private LinearLayout mFooterView;

    public PhotoAdapter(Context context, List<Photos> data) {
        super(context, data);
    }

    public void setList(List<Photos> photoses) {
        mList = photoses;
    }

    private List<Trailers> mData;

    public void setHeadData(List<Trailers> data) {
        mData = data;
    }

    public void setFooterData(int count) {
        stills_count = count;
    }


    @Override
    int getLayoutId() {
        return R.layout.movie_detail_stills;
    }

    @Override
    void bindView(BaseItemViewHolder holder, int position) {
        holder.setImgUrl(R.id.iv_stills, mList.get(position).getImage());
        mContent = holder.getView(R.id.ll_stills);
        mContent.setOnClickListener(this);
        mContent.setTag(position);
    }

    @Override
    void bindHeadView(BaseItemViewHolder holder, int position) {
        if (position == 0 && mData.size() != 0) {
            holder.setImgUrl(R.id.iv_stills, mData.get(0).getMedium());
            holder.setText(R.id.tv_stills_date, "00:29");
        }
        mHeadView = holder.getView(R.id.ll_stills_head);
        mHeadView.setOnClickListener(this);
    }

    @Override
    void bindFooterView(BaseItemViewHolder holder, int position) {
        if (position == getItemCount() - 1) {
            holder.setText(R.id.tv_stills_count, stills_count + "张");
        }
        mFooterView = holder.getView(R.id.ll_stills_footer);
        mFooterView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_stills:
                int position = (int) v.getTag();
                mClick.onItemClick(position, TAG);
                break;
            case R.id.ll_stills_head:
                mClick.onItemClick(0, HEAD);
                break;
            case R.id.ll_stills_footer:
                mClick.onItemClick(getItemCount() - 1, FOOTER);
                break;
        }

    }
}
