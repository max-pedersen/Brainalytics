package org.maxpedersen.maquiz;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;




    @Entity(tableName = "user_result_join",
            primaryKeys = { "userId", "resultId" },
            foreignKeys = {
                    @ForeignKey(entity = User.class,
                            parentColumns = "z_id",
                            childColumns = "userId"),
                    @ForeignKey(entity = Result.class,
                            parentColumns = "id",
                            childColumns = "resultId")
            })
    public class UserResultJoin {
        public final int userId;
        public final int resultId;

        public UserResultJoin(final int userId, final int resultId) {
            this.userId = userId;
            this.resultId = resultId;
        }
    }

