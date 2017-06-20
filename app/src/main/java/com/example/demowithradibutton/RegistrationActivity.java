package com.example.demowithradibutton;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {
    public EditText Uname, Email, Phonenum;
    public Button cancel, save, showdata;
    public RadioGroup rbtngender;
    public RadioButton rbtnmale, rbtnfemale, radioSexButton;
    public String Name, email, phoneno, Gender, selection;
    private boolean isupdate;
    Databasehelper dbhelper;
    int Id;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);
        Uname = (EditText) findViewById(R.id.editusername);
        Email = (EditText) findViewById(R.id.editemail);
        Phonenum = (EditText) findViewById(R.id.editphone);
        rbtngender = (RadioGroup) findViewById(R.id.radiogender);
        rbtnmale = (RadioButton) findViewById(R.id.radiomale);
        rbtnfemale = (RadioButton) findViewById(R.id.radiofemale);
        save = (Button) findViewById(R.id.btninsert);
        cancel = (Button) findViewById(R.id.btncancel);
        showdata = (Button) findViewById(R.id.btnshowdata);

        toolbar=(Toolbar)findViewById(R.id.tool_bar);
        Toolbar toolbarTop = (Toolbar) findViewById(R.id.tool_bar);
        TextView Title = (TextView) toolbarTop.findViewById(R.id.tooltitle);
        Title.setText("User Registration");
        Title.setTextColor(Color.parseColor("#F5F5F5"));
        setSupportActionBar(toolbar);

        dbhelper = new Databasehelper(this);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uname.getText().clear();
                Email.getText().clear();
                Phonenum.getText().clear();
                //rbtngender.clearCheck();
                rbtngender.check(R.id.radiomale);

            }
        });

        Bundle intent = getIntent().getExtras();
        isupdate = intent.getBoolean("update");
        if (isupdate) {
            Id = intent.getInt("Id");
            String name = intent.getString("Uname");
            String email = intent.getString("UEmail");
            String phone = getIntent().getStringExtra("Umobile");
            String gender = getIntent().getStringExtra("Ugender");

            Uname.setText(name);
            Email.setText(email);
            Phonenum.setText(phone);
            if(gender.equals("Male")){
                rbtngender.check(R.id.radiomale);
            }
            else {
                rbtngender.check(R.id.radiofemale);
            }

        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = Uname.getText().toString().trim();
                email = Email.getText().toString().trim();
                phoneno = Phonenum.getText().toString();
                 Gender = ((RadioButton) rbtngender.findViewById(rbtngender.getCheckedRadioButtonId())).getText().toString();

                User U = new User(Id, Name, email, phoneno, Gender);
                if (isupdate) {

                    dbhelper.Updateuser(U);
                    if (U != null) {
                        Toast.makeText(getApplicationContext(), "Data has been Updated !..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, Main2Activity.class);
                        startActivity(intent);
                        Intent i = new Intent(RegistrationActivity.this, Main2Activity.class);
                        // set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);

                    }
                } else {
                    User user = new User(Name, email, phoneno, Gender);
                    dbhelper.adduser(user);
                    if (user != null) {
                        Toast.makeText(getApplicationContext(), "Data has been inserted !..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistrationActivity.this, Main2Activity.class);
                        startActivity(intent);

                    }

                }


                Log.d("phone", String.valueOf(phoneno));
                Log.d("gender", String.valueOf(Gender));
                /*Uname.getText().clear();
                Email.getText().clear();
                Phonenum.getText().clear();
                rbtngender.clearCheck();*/

            }
        });
        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        for(int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(Color.parseColor("#ECF0F1")), 0, spanString.length(), 0); //fix the color to white
            int end = spanString.length();
            spanString.setSpan(new RelativeSizeSpan(1.1f), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            item.setTitle(spanString);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.showdata) {
            Intent intent = new Intent(RegistrationActivity.this,Main2Activity.class);
           /* intent.putExtra("update", false);*/
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
