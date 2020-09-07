package mvp.wyyne.douban.moviedouban.utils;



import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import mvp.wyyne.douban.moviedouban.api.bean.Subjects;

public class AdapterDiff extends DiffUtil.Callback {
    private List<Subjects> oldList;
    private List<Subjects> newList;


    public AdapterDiff(List<Subjects> oldList, List<Subjects> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override

    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getClass().equals(newList.get(newItemPosition).getClass());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        String oldName = oldList.get(oldItemPosition).getTitle();
        int oldCount = oldList.get(oldItemPosition).getCollect_count();
        double oldGrade = oldList.get(oldItemPosition).getRating().getAverage();
        String oldPic = oldList.get(oldItemPosition).getImages().getMedium();
        String newName = newList.get(oldItemPosition).getTitle();
        int newCount = newList.get(oldItemPosition).getCollect_count();
        double newGrade = newList.get(oldItemPosition).getRating().getAverage();
        String newPic = newList.get(oldItemPosition).getImages().getMedium();

        return oldName.equals(newName) && oldCount == newCount && oldGrade == newGrade && oldPic.equals(newPic);
    }
}
