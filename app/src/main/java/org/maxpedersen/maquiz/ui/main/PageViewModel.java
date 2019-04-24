package org.maxpedersen.maquiz.ui.main;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import org.maxpedersen.maquiz.Content;
import org.maxpedersen.maquiz.ContentDetailedSlide;

import java.util.ArrayList;
import java.util.List;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            int i = ContentDetailedSlide.getI();
            List<Content> list = Content.getContent();
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

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }
}