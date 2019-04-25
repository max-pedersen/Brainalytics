package org.maxpedersen.maquiz;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import org.maxpedersen.maquiz.R;

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
        View view = inflater.inflate(R.layout.fragment_leaderboard,container, false);
        useresults = DatabaseService.getDbInstance(context).getAppDatabase().userResultJoinDAO().getTopUsers();
        tl = view.findViewById(R.id.tableLayout);
        inflateTable(tl);
        return view;
    }


    //This method sets out the table layout dynamically based on the UserResultJoin list returned by the getTopUsers method
    private void inflateTable(TableLayout tableLayout){
        //For loop to go through the list and populate the database, i=10 because we only want to limite the leaderboard to top 10
        for(int i = 0; i < 10; i++){
            TableRow row = new TableRow(context);
            row.setGravity(Gravity.CENTER);
            row.setWeightSum(3);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            TableLayout.LayoutParams tp = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT);
            tp.weight = 1;
            tp.topMargin = 10;
            tp.leftMargin = 1;
            tp.rightMargin = 1;
            tp.bottomMargin = 10;
            tp.gravity = Gravity.CENTER;
            TableRow.LayoutParams lp = (TableRow.LayoutParams) row.getLayoutParams();
            lp.weight = 1;
            lp.width = 0;
            lp.topMargin = 75;
            lp.leftMargin = 50;
            lp.rightMargin = 50;
            lp.bottomMargin = 75;
            String name = useresults.get(i).first_name;
            int xpInt = (useresults.get(i).total_score)*10;
            String xp = Integer.toString(xpInt);
            ImageView iv = new ImageView(context);
            TextView nameTV =new TextView(context);
            TextView xpTV =new TextView(context);
            iv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            nameTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            xpTV.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            iv.setImageResource(R.drawable.ic_person_outline_white_24dp);
            nameTV.setText(name);
            xpTV.setText(xp);
            nameTV.setTextAppearance(R.style.AppTheme);
            xpTV.setTextAppearance(R.style.AppTheme);
            iv.setLayoutParams(lp);
            nameTV.setLayoutParams(lp);
            xpTV.setLayoutParams(lp);
            row.addView(iv);
            row.addView(nameTV);
            row.addView(xpTV);
            tableLayout.addView(row);
            row.setLayoutParams(tp);
            row.setBackgroundColor(Color.parseColor("#D0C0C0C0"));
        }
    }
}
