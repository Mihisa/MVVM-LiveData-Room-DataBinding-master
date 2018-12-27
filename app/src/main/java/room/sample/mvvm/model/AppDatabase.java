package room.sample.mvvm.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {UserModel.class}, version = 1, exportSchema = false)
public abstract  class AppDatabase extends RoomDatabase {

    private static AppDatabase mInstance ;
    public static AppDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context, AppDatabase.class,
                    "emp").build();

        }
        return mInstance;
    }

    public abstract UserModelDao getEmpModel();
}
