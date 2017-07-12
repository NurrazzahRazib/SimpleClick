package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    RadioButton rbmGender;
    RadioButton rbfGender;
    CheckBox ckbLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etName = (EditText) findViewById(R.id.editText);
        etGPA = (EditText) findViewById(R.id.editText2);
        rgGender=(RadioGroup) findViewById(R.id.RadioGroupGender);
        rbmGender=(RadioButton)findViewById(R.id.radioButtonGenderMale);
        rbfGender=(RadioButton)findViewById(R.id.radioButtonGenderFemale);
        ckbLike=(CheckBox)findViewById(R.id.checkBoxLikeProgramming);



    }

    @Override
    protected void onPause() {
        super.onPause();
        //Step 1a: Retrieve data input of the user
        String strName= etName.getText().toString();
        String strGPA = etGPA.getText().toString();
        float ftGPA =  Float.parseFloat(strGPA);
        int rbCheckID= rgGender.getCheckedRadioButtonId();
        boolean cbCheck=ckbLike.isChecked();


        //Step 1b:Obtain an instance of the shared preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //Step 1c:Obtain an instance of the Shared preference Editor for update later
        SharedPreferences.Editor prefEdit=prefs.edit();

        //Step 1d:Add the key-value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("GPA", ftGPA);
        prefEdit.putFloat("ID", rbCheckID);
        prefEdit.putBoolean("Check", cbCheck);


        //Step 1e:Call commit() method to save the changes into the shared preference
        prefEdit.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a:Obtain an instance of the shared preference
        SharedPreferences prefs =PreferenceManager.getDefaultSharedPreferences(this);

        //Step 2b: Retrieve the saved data with the key, name from the SharedPreferences object
        String strName=prefs.getString("name", "John");
        Float ftGPA=prefs.getFloat("GPA", 0);
        int rbCheckID =prefs.getInt("id", R.id.radioButtonGenderMale);
        boolean cbCheck=prefs.getBoolean("check", false);




        //Step 2c: Update the UI element with the value.
        etName.setText(strName);
        etGPA.setText(Float.toString(ftGPA));
        rgGender.check(rbCheckID);
        ckbLike.setChecked(cbCheck);

    }
}