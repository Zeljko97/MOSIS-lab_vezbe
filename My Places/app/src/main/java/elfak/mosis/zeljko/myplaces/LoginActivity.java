package elfak.mosis.zeljko.myplaces;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    Button regBtn;
    FirebaseAuth mAuth;
    EditText emailText;
    EditText passwordText;
    ProgressBar bar;

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        emailText = (EditText)findViewById(R.id.EmailText);
        passwordText = (EditText)findViewById(R.id.PasswordText);
        bar = (ProgressBar)findViewById(R.id.progressBar);
        loginBtn = (Button)findViewById(R.id.ButtonLogin);
        regBtn = (Button)findViewById(R.id.ButtonRegister);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

    }

    private void userLogin()
    {
        String mail = emailText.getText().toString().trim();
        String psw = passwordText.getText().toString().trim();

        if(mail.isEmpty()) {
            emailText.setError("E-mail is required");
            emailText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            emailText.setError("Please enter valid email");
            emailText.requestFocus();
            return;
        }
        if(psw.isEmpty()) {
            passwordText.setError("Password is required");
            passwordText.requestFocus();
            return;
        }
        if(psw.length() < 6) {
            passwordText.setError("Minimum password length should be 6");
            passwordText.requestFocus();
            return;
        }

        bar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(mail, psw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                bar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    finish();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}
