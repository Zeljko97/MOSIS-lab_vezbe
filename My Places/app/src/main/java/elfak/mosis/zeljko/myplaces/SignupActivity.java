package elfak.mosis.zeljko.myplaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.net.URI;

public class SignupActivity extends AppCompatActivity {

    private static final int CHOOSE_IMAGE = 101;
    EditText username;
    EditText password;
    EditText repeat_password;
    Button signBtn;
    ProgressBar pbar;
    private FirebaseAuth mAuth;
    private Handler mWaitHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mWaitHandler = new Handler();
        mAuth = FirebaseAuth.getInstance();
        pbar = (ProgressBar)findViewById((R.id.progressBar2));
        username = (EditText)findViewById(R.id.EmailTextSignUp);
        password = (EditText)findViewById(R.id.PasswordTextSignUp);
        repeat_password = (EditText)findViewById(R.id.RepeatPasswordText);
        signBtn = (Button) findViewById(R.id.SignUp2);
        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {

        String email = username.getText().toString().trim();
        String pw = password.getText().toString().trim();
        String rpw = repeat_password.getText().toString().trim();

        if(email.isEmpty()) {
            username.setError("E-mail is required");
            username.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            username.setError("Please enter valid email");
            username.requestFocus();
            return;
        }
        if(pw.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(pw.length() < 6) {
            password.setError("Minimum password length should be 6");
            password.requestFocus();
            return;
        }
        if(!rpw.equals(pw)){
            repeat_password.setError("Passwords don't match");
            repeat_password.requestFocus();
            return;
        }
        if(rpw.isEmpty()) {
            repeat_password.setError("Repeat password is required");
            repeat_password.requestFocus();
            return;
        }

        pbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                pbar.setVisibility(View.GONE);
                if(task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "User successfully registered!", Toast.LENGTH_SHORT).show();
                    mWaitHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    }, 2000);
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "Email is already registered!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }


}
