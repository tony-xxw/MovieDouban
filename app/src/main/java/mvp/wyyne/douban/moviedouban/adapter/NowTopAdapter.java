package mvp.wyyne.douban.moviedouban.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import mvp.wyyne.douban.moviedouban.AndroidApplication;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.api.model.WannaModel;
import mvp.wyyne.douban.moviedouban.login.LoginActivity;

import static mvp.wyyne.douban.moviedouban.utils.Constant.NOW_TAG;
import static mvp.wyyne.douban.moviedouban.utils.Constant.TOP_TAG;
import static mvp.wyyne.douban.moviedouban.utils.Constant.WANNA;
import static mvp.wyyne.douban.moviedouban.utils.Constant.WANNA_EXIST;

/**
 * @author Wynne
 * @date 2018/6/16
 */

public class NowTopAdapter extends BaseRvAdapter<Subjects> {
    private String TAG;

    public NowTopAdapter(Context context, List<Subjects> data) {
        super(context, data);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_now_top;
    }

    public void setTag(String tag) {
        TAG = tag;
    }

    @Override
    public void bindView(final BaseItemViewHolder holder, final int position) {

        holder.setText(R.id.tv_title, mList.get(position).getTitle());
        holder.setImgUrl(R.id.iv_avatar, mList.get(position).getImages().getSmall());
        String average = (int) mList.get(position).getRating().getAverage() + "";
        holder.getStartView(R.id.average).setStartMark((int) mList.get(position).getRating().getAverage());
        holder.setText(R.id.tv_average_count, average);

        if (TAG.equals(TOP_TAG)) {
            holder.getView(R.id.iv_add).setVisibility(View.GONE);
        }

        if (WannaModel.getInstance().queryRecord(mList.get(position).getTitle())) {
            holder.getView(R.id.iv_add).setBackgroundColor(mContext.getResources().getColor(R.color.colorOrange));
            holder.setImgResource(R.id.iv_add, R.drawable.ic_subject_checked);
        } else {
            holder.getView(R.id.iv_add).setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
            holder.setImgResource(R.id.iv_add, R.drawable.ic_subject_mark_add);
        }

        holder.getView(R.id.ll_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.onItemClick(position, TAG);
            }
        });

        holder.getView(R.id.iv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AndroidApplication.getApplication().isLogin()) {
                    //有记录
                    if (WannaModel.getInstance().queryRecord(mList.get(position).getTitle())) {
                        holder.getView(R.id.iv_add).setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
                        holder.setImgResource(R.id.iv_add, R.drawable.ic_subject_mark_add);
                        if (TAG.equals(NOW_TAG)) {
                            mClick.onItemClick(position, WANNA_EXIST);
                        }

                    } else {
                        holder.getView(R.id.iv_add).setBackgroundColor(mContext.getResources().getColor(R.color.colorOrange));
                        holder.setImgResource(R.id.iv_add, R.drawable.ic_subject_checked);
                        if (TAG.equals(NOW_TAG)) {
                            mClick.onItemClick(position, WANNA);
                        }

                    }
                } else {
                    mContext.startActivity(new Intent(mContext, LoginActivity.class));
                }
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
