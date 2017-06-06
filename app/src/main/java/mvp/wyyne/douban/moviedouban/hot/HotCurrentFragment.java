package mvp.wyyne.douban.moviedouban.hot;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.Subject;
import mvp.wyyne.douban.moviedouban.R;
import mvp.wyyne.douban.moviedouban.adapter.CurrentAdapter;
import mvp.wyyne.douban.moviedouban.api.RetrofitService;
import mvp.wyyne.douban.moviedouban.api.bean.HotBean;
import mvp.wyyne.douban.moviedouban.api.bean.Subjects;
import mvp.wyyne.douban.moviedouban.main.BaseFragment;

/**
 * Created by XXW on 2017/6/4.
 */

public class HotCurrentFragment extends BaseFragment {
    protected static final String TAG ="HotCurrentFragment";
    @BindView(R.id.current_rv)
    RecyclerView mCurrentRv;
    private List<Subjects> mList;
    private CurrentAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot_current;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mAdapter = new CurrentAdapter(getActivity(), mList);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        mCurrentRv.setLayoutManager(manager);
        mCurrentRv.addItemDecoration(new DividerItemDecoration(mCurrentRv.getContext(), manager.getOrientation()));
        mCurrentRv.setAdapter(mAdapter);


        RetrofitService.getHotList()
                .subscribe(new Observer<HotBean>() {
                               @Override
                               public void onSubscribe(@NonNull Disposable d) {

                               }

                               @Override
                               public void onNext(@NonNull HotBean hotBeen) {
                                   mAdapter.setList(hotBeen.getSubjectsList());
                                   mAdapter.notifyDataSetChanged();

                                   Log.d("XXW", hotBeen.getSubjectsList().size() + "");
                               }

                               @Override
                               public void onError(@NonNull Throwable e) {
                                   Log.d("XXW", e.toString());
                               }

                               @Override
                               public void onComplete() {

                               }
                           }
                );

    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

}
