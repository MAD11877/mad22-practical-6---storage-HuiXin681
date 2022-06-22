package sg.np.edu.mad.exercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);


        Button grpBtn1 = findViewById(R.id.grp1);
        Button grpBtn2 = findViewById(R.id.grp2);
        grpBtn1.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentTransaction f = getSupportFragmentManager().beginTransaction();
                f.replace(R.id.frame1, new msgGrp1());
                f.commit();
            }
        });
        grpBtn2.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FragmentTransaction f = getSupportFragmentManager().beginTransaction();
                f.replace(R.id.frame1, new msgGrp2());
                f.commit();
            }
        });
    }
}