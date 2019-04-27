package org.maxpedersen.maquiz;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import org.maxpedersen.maquiz.Content;
import org.maxpedersen.maquiz.ContentDetailedSlide;

import java.util.ArrayList;
import java.util.List;

public class PageViewModel extends AndroidViewModel {

    //This class determines what data should be inputted into the content slide fragments
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        //This method determines how many fragements are in the activity by getting the i value from Sections Pager Adapter. Based on this value the if
        //statements return the content in relation to that page
        @Override
        public String apply(Integer input) {
            int i = ContentDetailedSlide.getI();
            List<Content> list = (ArrayList<Content>) DatabaseService.getDbInstance(getApplication().getApplicationContext()).getAppDatabase()
                    .contentDAO().getContents();;
            if(input == 1){
                return list.get(i).getContent_page1();

            }
            if(input == 2){
                return list.get(i).getContent_page2();
            }
            if(input == 3){
                return list.get(i).getContent_page3();
            }
            if(input == 4){
                return list.get(i).getContent_page4();
            }
            else {
                return "Hello world from section: " + input;
            }
        }
    });

    public PageViewModel(@NonNull Application application) {
        super(application);
    }

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}