package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.maquiz.R;
import com.google.common.collect.Table;

import java.util.List;

public class LeaderboardFragment extends Fragment {
    private Context context;
    TableLayout tl;

    TextView text;
    List<UserResultJoin> useresults;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class,
                "Overall Database").allowMainThreadQueries().build();

        View view = inflater.inflate(R.layout.fragment_leaderboard,container, false);
        useresults = db.userResultJoinDAO().getTopUsers();

        Log.d(" from the method", useresults.get(0).first_name);
        Log.d(" from the method", useresults.get(1).first_name);
        Log.d(" from the method", useresults.get(2).first_name);
        Log.d(" from the method", useresults.get(3).first_name);
        Log.d(" from the method", useresults.get(4).first_name);
        Log.d(" from the method", useresults.get(5).first_name);
        tl = (TableLayout) view.findViewById(R.id.fuckyouJulian);
        inflateTable(tl);
        return view;
    }

    private void inflateTable(TableLayout tableLayout){

        for(int i = 0; i < 9; i++){
            TableRow row = new TableRow(context);
            row.setGravity(Gravity.CENTER);
            row.setWeightSum(3);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

            TableLayout.LayoutParams tp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
            tp.weight = 1;
            tp.topMargin = 1;
            tp.leftMargin = 1;
            tp.rightMargin = 1;
            tp.bottomMargin = 1;
            tp.gravity = Gravity.CENTER;

            TableRow.LayoutParams lp = (TableRow.LayoutParams) row.getLayoutParams();
            lp.weight = 1;
            lp.width = 0;
            lp.topMargin = 1;
            lp.leftMargin = 1;
            lp.rightMargin = 1;
            lp.bottomMargin = 1;
            tp.gravity = Gravity.CENTER;

//            row.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//            tableLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            String name = useresults.get(i).first_name;
            Log.d("Name Leaderboard", name);
            int xpInt = useresults.get(i).total_score;
            String xp = Integer.toString(xpInt);
            Log.d("XP Leaderboard", xp);

            ImageView iv = new ImageView(context);
            TextView nameTV =new TextView(context);
            TextView xpTV =new TextView(context);


            nameTV.setText(name);
            nameTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            xpTV.setText(xp);
            xpTV.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            iv.setLayoutParams(lp);
            nameTV.setLayoutParams(lp);
            xpTV.setLayoutParams(lp);

            iv.setForegroundGravity(11);
            nameTV.setForegroundGravity(11);
            xpTV.setForegroundGravity(11);

//            iv.setBackgroundColor(getResources().getColor(R.color.Red));
//            nameTV.setBackgroundColor(getResources().getColor(R.color.Red));
//            xpTV.setBackgroundColor(getResources().getColor(R.color.Red));

            row.addView(iv);
            row.addView(nameTV);
            row.addView(xpTV);

            tableLayout.addView(row);
            row.setLayoutParams(tp);
        }
    }
}
