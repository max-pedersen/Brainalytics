package org.maxpedersen.maquiz;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
    /*This class joins together the attributes of User and Result that are needed, to allow for SQL join queries
    between the two entities */

    public class UserResultJoin {
        public final String first_name;
        @PrimaryKey @NonNull
        public final String z_id;
        public final int total_score;


        public UserResultJoin(final String first_name, final String z_id, final int total_score) {
            this.first_name = first_name;
            this.z_id = z_id;
            this.total_score = total_score;
        }


    }

