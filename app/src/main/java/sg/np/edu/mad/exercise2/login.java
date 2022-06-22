package sg.np.edu.mad.exercise2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");
        DatabaseReference nested = myRef.child("mad").push();
        DatabaseReference myRef2 = database.getReference("mad");
        DatabaseReference username = myRef2.child("username:").push();
        DatabaseReference password = myRef2.child("password:").push();
        username.setValue("11877");
        password.setValue("mad");

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef2.child("username:").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String usernameData = snapshot.getChildren().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef2.child("password:").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String passwordData = snapshot.getChildren().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("Failed to get val");
                    }
                });
                EditText loginUsername = findViewById(R.id.usernameInput);
                EditText loginPassword = findViewById(R.id.passwordInput);
                String loginUsername_string = loginUsername.getText().toString();
                String loginPassword_string = loginPassword.getText().toString();

                if (loginUsername_string == loginUsername_string && loginPassword_string== loginPassword_string);
                {
                    Intent intent = new Intent(login.this, MainActivity.class);
                }

            }
        });
    }
}