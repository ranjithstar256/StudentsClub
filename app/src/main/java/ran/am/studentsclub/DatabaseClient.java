package ran.am.studentsclub;


import android.content.Context;

import androidx.room.Room;

class DatabaseClienttt {

    private Context mCtx;
    private static DatabaseClienttt mnInstancee;
    private AppDatabase appDatabase;

    private DatabaseClienttt(Context mCtx) {
        this.mCtx = mCtx;
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, "studentsclubdb").build();
    }

    public static synchronized DatabaseClienttt getInstancce(Context mCtx) {
        if (mnInstancee == null) {
            mnInstancee = new DatabaseClienttt(mCtx);
        }
        return mnInstancee;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
