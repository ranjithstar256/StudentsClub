package ran.am.studentsclub;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Details.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract  TaskDao taskDao();
}