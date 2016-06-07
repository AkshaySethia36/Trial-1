package com.example.seetharaman.trial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText firstNameET, lastNameET, emailET, phoneET, collegeET;
    private TextInputLayout tilFirstName, tilLastName, tilEmail, tilPhone, tilSchoolName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.event_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.events_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assert spinner != null;
        spinner.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert toolbar != null;
        toolbar.setTitle("Registrations");

        firstNameET = (EditText) findViewById(R.id.et_first_name);
        lastNameET = (EditText) findViewById(R.id.et_last_name);
        emailET = (EditText) findViewById(R.id.et_email);
        phoneET = (EditText) findViewById(R.id.et_phone);
        collegeET = (EditText) findViewById(R.id.et_school_name);


        tilFirstName = (TextInputLayout) findViewById(R.id.til_first_name);
        tilLastName = (TextInputLayout) findViewById(R.id.til_last_name);
        tilEmail = (TextInputLayout) findViewById(R.id.til_email);
        tilPhone = (TextInputLayout) findViewById(R.id.til_phone);
        tilSchoolName = (TextInputLayout) findViewById(R.id.til_school_name);

        firstNameET.addTextChangedListener(new MyTextWatcher(firstNameET));
        lastNameET.addTextChangedListener(new MyTextWatcher(lastNameET));
        emailET.addTextChangedListener(new MyTextWatcher(emailET));
        phoneET.addTextChangedListener(new MyTextWatcher(phoneET));
        collegeET.addTextChangedListener(new MyTextWatcher(collegeET));

    }

    public void submitInfo(View view) {
        //Check whether the inputs given by user are valid:
        Boolean isValid = checkValidity();
        hideKeyboard();

        if (isValid) {
            Toast submitToast = Toast.makeText(getApplicationContext(), "Successfully submitted!", Toast.LENGTH_SHORT);
            submitToast.show();

            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivity(intent);
        }
        else {
            Toast submitToast = Toast.makeText(getApplicationContext(), "Please provide valid details", Toast.LENGTH_SHORT);
            submitToast.show();
        }
    }

    public Boolean checkValidity() {
        Boolean isValid;

        //Check validity of First Name
        isValid = isValidFirstName();
        if (!isValid) return isValid;

        //Check validity of Last Name
        isValid = isValidLastName();
        if (!isValid) return isValid;

        //Check validity of Email Address
        isValid = isValidEmail();
        if (!isValid) return isValid;

        //Check validity of Phone Number
        isValid = isValidPhone();
        if (!isValid) return isValid;

        //Check validity of School Name
        isValid = isValidSchoolName();
        if (!isValid) return isValid;

        return isValid;
    }

    //Check validity of First Name
    public boolean isValidFirstName() {
        Boolean isValid = true;
        String firstName = firstNameET.getText().toString().trim();
        if (firstName.length() == 0) {
            tilFirstName.setError("Field cannot be blank");
            isValid = false;
        } else if (!firstName.matches("[a-zA-Z ]+")) {
            tilFirstName.setError("Enter valid First Name");
            isValid = false;
        } else {
            tilFirstName.setErrorEnabled(false);
        }
        return isValid;
    }

    //Check validity of Last Name
    public boolean isValidLastName() {
        Boolean isValid = true;
        String lastName = lastNameET.getText().toString().trim();
        if (lastName.isEmpty()) {
            tilLastName.setError("Field cannot be blank");
            isValid = false;
        } else if (!lastName.matches("[a-zA-Z ]+")) {
            tilLastName.setError("Enter valid First Name");
            isValid = false;
        } else {
            tilLastName.setErrorEnabled(false);
        }
        return isValid;
    }

    //Check validity of Email Address
    public boolean isValidEmail() {
        Boolean isValid;
        String email = emailET.getText().toString().trim();
        if (email.isEmpty()) {
            tilEmail.setError("Field cannot be blank");
            isValid = false;
        } else {
            isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
            if (!isValid) {
                tilEmail.setError("Enter a valid Email Address");
            } else tilEmail.setErrorEnabled(false);
        }
        return isValid;
    }

    //Check validity of Phone Number
    public boolean isValidPhone() {
        Boolean isValid;
        String phone = phoneET.getText().toString().trim();
        if (phone.isEmpty()) {
            tilPhone.setError("Field cannot be blank");
            isValid = false;
        } else {
            isValid = android.util.Patterns.PHONE.matcher(phone).matches();
            if (!isValid) {
                tilPhone.setError("Enter a valid Phone Number");
            } else tilPhone.setErrorEnabled(false);
        }
        return isValid;
    }

    //Check validity of School Name
    public boolean isValidSchoolName() {
        Boolean isValid = true;
        String college = collegeET.getText().toString().trim();
        if (college.isEmpty()) {
            tilSchoolName.setError("Field cannot be blank");
            isValid = false;
        } else if (!college.matches("[a-zA-Z ]+")) {
            tilSchoolName.setError("Enter valid School/College Name");
            isValid = false;
        } else {
            tilSchoolName.setErrorEnabled(false);
        }
        return isValid;
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_first_name:
                    isValidFirstName();
                    break;
                case R.id.et_last_name:
                    isValidLastName();
                    break;
                case R.id.et_email:
                    isValidEmail();
                    break;
                case R.id.et_phone:
                    isValidPhone();
                    break;
                case R.id.et_school_name:
                    isValidSchoolName();
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

