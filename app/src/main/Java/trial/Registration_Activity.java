package trial;


import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.seetharaman.trial.R;

import java.util.ArrayList;
import java.util.List;

public class Registration_Activity extends ActionBarActivity{

    TextInputLayout til_first_name, til_last_name, til_email, til_phone_number, til_college_name;
    ImageView im_first_name, im_email, im_phone_number, im_college_name;
    String first_name, last_name, email, phone_number, college_name;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";//For validating the e-mail

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_page);//Setting content view on registration_page

        //Setting up the toolbar
        Toolbar t_reg_toolbar=(Toolbar)findViewById(R.id.t_reg_toolbar);
        t_reg_toolbar.setTitle("Registration Page");
        t_reg_toolbar.setTitleTextColor(getResources().getColor(R.color.colorIcons));
        setSupportActionBar(t_reg_toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.colorIcons), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        til_first_name=(TextInputLayout)findViewById(R.id.til_first_name);
        til_last_name=(TextInputLayout)findViewById(R.id.til_last_name);
        til_email=(TextInputLayout)findViewById(R.id.til_email);
        til_phone_number=(TextInputLayout)findViewById(R.id.til_phone_number);
        til_college_name=(TextInputLayout)findViewById(R.id.til_college_name);


        Spinner s_events_list= (Spinner) findViewById(R.id.s_events_list);
        List<String> event = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_layout, event);
        event.add("Event_1");
        event.add("Event_2");
        event.add("Event_3");
        event.add("Event_4");
        event.add("Event_5");
        assert s_events_list!= null;
        s_events_list.setAdapter(adapter);



        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Adding a tick mark in front of the text fields which are correctly filled
        final EditText et_first_name=(EditText)findViewById(R.id.et_first_name);
        //Checking is focus i changed from the text field
        et_first_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (et_first_name.getText().toString().trim().length() != 0) {
                        Drawable dr = getResources().getDrawable(R.drawable.check);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        et_first_name.setCompoundDrawables(null, null, dr, null);
                        til_first_name.setError(" ");
                    } else {//Removing tick mark from the field if incorrectly filled
                        et_first_name.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
        });

        final EditText et_last_name=(EditText)findViewById(R.id.et_last_name);
        et_last_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (et_last_name.getText().toString().trim().length() != 0) {
                        Drawable dr = getResources().getDrawable(R.drawable.check);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        et_last_name.setCompoundDrawables(null, null, dr, null);
                    }
                    else {
                        et_last_name.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
        });


        final EditText et_email=(EditText)findViewById(R.id.et_email);
        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (et_email.getText().toString().matches(emailPattern)) {
                        Drawable dr = getResources().getDrawable(R.drawable.check);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        et_email.setCompoundDrawables(null, null, dr, null);
                        til_email.setError(" ");
                    } else {
                        et_email.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
        });


        final EditText et_phone_number=(EditText)findViewById(R.id.et_phone_number);
        et_phone_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (et_phone_number.getText().toString().length() == 10) {
                        Drawable dr = getResources().getDrawable(R.drawable.check);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        et_phone_number.setCompoundDrawables(null, null, dr, null);
                        til_phone_number.setError(" ");
                    } else {
                        et_phone_number.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
        });


        final EditText et_college_name=(EditText)findViewById(R.id.et_college_name);
        et_college_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (et_college_name.getText().toString().trim().length() != 0) {
                        Drawable dr = getResources().getDrawable(R.drawable.check);
                        dr.setBounds(0, 0, dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
                        et_college_name.setCompoundDrawables(null, null, dr, null);
                    } else {
                        et_college_name.setCompoundDrawables(null, null, null, null);
                    }
                }
            }
        });



    }

    //Setting up the action for the submit button
    public void submit(View view) {

        first_name = til_first_name.getEditText().getText().toString();
        last_name = til_last_name.getEditText().getText().toString();
        email = til_email.getEditText().getText().toString();
        phone_number = til_phone_number.getEditText().getText().toString();
        college_name = til_college_name.getEditText().getText().toString();

        //Setting up the error messages
        if (first_name.trim().length() == 0)
            til_first_name.setError("Field cannot remain empty");
        else
            til_first_name.setError(" ");

        if (!email.matches(emailPattern))
            til_email.setError("Invalid e-mail address");
        else
            til_email.setError(" ");

        if (phone_number.length() < 10)
            til_phone_number.setError("Invalid phone number");
        else
            til_phone_number.setError(" ");

        //Sending a message if all the data is correctly filled via Toast
        if (first_name.trim().length() != 0 && email.matches(emailPattern) && phone_number.length() == 10)
        {
            Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
        }
    }

}

