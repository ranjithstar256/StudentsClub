package ran.am.studentsclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import ran.am.studentsclub.databinding.ActivityShowDataBinding;
import ran.am.studentsclub.databinding.ActivitySignUpBinding;

public class ShowDataActivity extends AppCompatActivity {
    ActivityShowDataBinding a;
    String rollnum;

    Details details;
    int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a = DataBindingUtil.setContentView(this, R.layout.activity_show_data);

        rollnum = getIntent().getStringExtra("rollnum");

        try {

            if (rollnum.equals("")) {

            } else {
                SaveTask st = new SaveTask();
                st.execute();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void lod(View view) {

        rollnum = a.editTextTextPersonName.getText().toString();
        if (rollnum.equals("")) {

        } else {
            mode = 1;
            SaveTask st = new SaveTask();
            st.execute();
        }
    }

    public void upd(View view) {
        mode = 2;
        details = new Details();
        details.setName(a.edname.getText().toString());
        details.setMobile(a.edmob.getText().toString());
        details.setPassword(a.edpw.getText().toString());
        details.setRollNo(a.edrol.getText().toString());
        SaveTask st = new SaveTask();
        st.execute();
    }

    public void deldata(View view) {
        mode = 3;
        SaveTask st = new SaveTask();
        st.execute();
        Toast.makeText(this, "Delete Success! ", Toast.LENGTH_SHORT).show();
    }

    class SaveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            if (mode == 1) {
                details = DatabaseClienttt.getInstancce(getApplicationContext()).getAppDatabase().taskDao().getdetails(rollnum);



            } else if (mode == 2) {

                DatabaseClienttt.getInstancce(getApplicationContext()).getAppDatabase().taskDao()
                        .updateItem(a.edrol.getText().toString(),
                                a.edname.getText().toString(),
                                a.edmob.getText().toString(),
                                a.edpw.getText().toString()
                                );
            } else if (mode==3){
                DatabaseClienttt.getInstancce(getApplicationContext()).getAppDatabase().taskDao()
                        .del(a.edrol.getText().toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                if (mode == 1) {
                    if (details.equals(null)){
                        Toast.makeText(ShowDataActivity.this, "No Data!", Toast.LENGTH_SHORT).show();
                    } else {
                        a.edname.setText(details.getName());
                        a.edmob.setText(details.getMobile());
                        a.edpw.setText(details.getPassword());
                        a.edrol.setText(details.getRollNo());
                    }
                } else if (mode == 2) {
                    Toast.makeText(ShowDataActivity.this, "Update Success!!", Toast.LENGTH_SHORT).show();
                }
            } catch (NullPointerException e){
                Log.d("2314TAG", "onPostExecute: "+e.getMessage());
                Toast.makeText(ShowDataActivity.this, "Data not Exists!!", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                e.printStackTrace();
                //Log.d("2314TAG", "onPostExecute: "+e.getMessage());
            }

        }
    }
}