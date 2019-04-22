package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maquiz.R;

import java.util.List;

public class LeaderboardFragment extends Fragment {
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class,
                "Overall Database").allowMainThreadQueries().build();

        List<UserResultJoin> useresults = db.userResultJoinDAO().getTopUsers();

        Log.d(" from the method", useresults.get(0).first_name);
        Log.d(" from the method", useresults.get(1).first_name);
        Log.d(" from the method", useresults.get(2).first_name);
        Log.d(" from the method", useresults.get(3).first_name);
        Log.d(" from the method", useresults.get(4).first_name);
        Log.d(" from the method", useresults.get(5).first_name);







        return inflater.inflate(R.layout.fragment_leaderboard,container, false);






    }
}
