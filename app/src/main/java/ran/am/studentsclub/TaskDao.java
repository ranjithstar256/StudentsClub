package ran.am.studentsclub;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao {

    @Insert
    void insert(Details details);

    @Query("SELECT * FROM Details WHERE RollNo LIKE :rollname ")
    Details getdetails(String rollname);

    @Query("UPDATE Details SET Name = :name ,Mobile= :mob,Password= :pw WHERE RollNo LIKE :rollno ")
    void updateItem(String rollno,String name,String mob,String pw);

    @Query("Delete from Details WHERE RollNo LIKE :rollno ")
    void del(String rollno);

    @Query("SELECT Password FROM Details WHERE RollNo LIKE :roll ")
    String rollnumber(String roll);


}
