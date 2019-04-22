package org.maxpedersen.maquiz;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
            // (tableName = "user_result_join",
            //primaryKeys = { "userId", "resultId" },
            /*foreignKeys = {
                    @ForeignKey(entity = User.class,
                            parentColumns = "z_id",
                            childColumns = "userId"),
                    @ForeignKey(entity = Result.class,
                            parentColumns = "id",
                            childColumns = "resultId")
            }) */
    public class UserResultJoin {
        //public final int userId;
       // public final int resultId;
        public final String first_name;
        @PrimaryKey @NonNull
        public final String z_id;
        public final int total_score;


        public UserResultJoin(
                //final int userId, final int resultId,

                              final String first_name,
                              final String z_id, final int total_score) {
            //this.userId = userId;
            //this.resultId = resultId;
            this.first_name = first_name;
            this.z_id = z_id;
            this.total_score = total_score;
        }


    }

