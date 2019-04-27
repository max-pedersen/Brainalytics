package org.maxpedersen.maquiz;

import android.app.Application;
import android.arch.core.util.Function;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import org.maxpedersen.maquiz.Content;
import org.maxpedersen.maquiz.ContentDetailedSlide;

import java.util.ArrayList;
import java.util.List;

public class PageViewModel extends AndroidViewModel {

    //This class determines what data should be inputted into the content slide fragments
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        //This method determines how many fragments are in the activity by getting the i value from Sections Pager Adapter. Based on this value the if
        //statements return the content in relation to that page
        @Override
        public String apply(Integer input) {
            int i = ContentDetailedSlide.getI();
            List<Content> list = (ArrayList<Content>) DatabaseService.getDbInstance(getApplication().getApplicationContext()).getAppDatabase()
                    .contentDAO().getContents();;
            if(input == 1){
                 String URI_TO_PASS = list.get(i).getContent_page1().split("URI:")[1];
                 Log.d("from youtube method", URI_TO_PASS);
                //return list.get(i).getContent_page1().split("URI:")[0]
                return URI_TO_PASS;

            }
            if(input == 2){
                String URI_TO_PASS = list.get(i).getContent_page2().split("URI:")[1];
                Log.d("from youtube method", URI_TO_PASS);
                return list.get(i).getContent_page2().split("URI:")[0];
            }
            if(input == 3){
                return list.get(i).getContent_page3().split("URI:")[0];
            }
            if(input == 4){
                return list.get(i).getContent_page4().split("URI:")[0];
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