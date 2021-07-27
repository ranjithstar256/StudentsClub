package ran.am.studentsclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ran.am.studentsclub.databinding.ActivityMainBinding;
import ran.am.studentsclub.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding a;
    String rollnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        a= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        rollnum="";
    }

    public void sup(View view) {
        SaveTask st = new SaveTask();
        st.execute();
    }

    public void lod(View view) {
        if (rollnum.equals("")){
            startActivity(new Intent(SignUpActivity.this,ShowDataActivity.class));

        }else {
            startActivity(new Intent(SignUpActivity.this,ShowDataActivity.class)
                    .putExtra("rollnum",rollnum));
        }
    }

    class SaveTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Details details = new Details();
            details.setName(a.edname.getText().toString());
            details.setMobile(a.edmob.getText().toString() );
            details.setPassword( a.edpw.getText().toString());
            details.setRollNo(a.edrol.getText().toString() );

            DatabaseClienttt.getInstancce(getApplicationContext()).getAppDatabase().taskDao().insert(details);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            rollnum=a.edrol.getText().toString();
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();


        }
    }
}