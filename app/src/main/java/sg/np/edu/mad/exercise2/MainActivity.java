package sg.np.edu.mad.exercise2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Practical 3
//        Intent recieveIntent = getIntent();
//        int randomNum = recieveIntent.getIntExtra("number", 0);
//        TextView username = findViewById(R.id.name);
//        String display = username.getText() + " " + randomNum;
//        username.setText(display);

        Intent recieveListActivityIntent = getIntent();
        String name = recieveListActivityIntent.getStringExtra("name");
        String desc = recieveListActivityIntent.getStringExtra("desc");
        boolean followTF = recieveListActivityIntent.getBooleanExtra("followTF", false);
        Integer id = recieveListActivityIntent.getIntExtra("id", 0);
        User u = new User(name, desc, id, followTF);

        TextView n = findViewById(R.id.name);
        n.setText(name);

        TextView d = findViewById(R.id.fillerText);
        d.setText(desc);
        Button following = findViewById(R.id.follow);
        if (followTF == true){
            following.setText("Follow");
        }
        else {
            following.setText("Unfollow");
        }

        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        following.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (following.getText() == "Follow") {
                    following.setText("Unfollow");
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                    u.followed = false;

                } else {
                    following.setText("Follow");
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                    u.followed = true;
                }

            }
        });

    }

    public void messageClick (View view){
        Intent messageView = new Intent(MainActivity.this, MessageGroup.class);
        startActivity(messageView);
    }
}
