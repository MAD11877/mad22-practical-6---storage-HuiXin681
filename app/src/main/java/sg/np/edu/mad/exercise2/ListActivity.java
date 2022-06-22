package sg.np.edu.mad.exercise2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        ImageView icon = findViewById(R.id.icon);
//        icon.setOnClickListener( view1 -> {
//            AlertDialog.Builder popup = new AlertDialog.Builder(ListActivity.this);
//            popup.setTitle("Profile");
//            popup.setMessage("MADness");
//            popup.setCancelable(true);
//            popup.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//
//                }
//            });
//            popup.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    Random randNm = new Random();
//                    int randNm1 = randNm.nextInt(Integer.MAX_VALUE);
//                    Intent i2 = new Intent(ListActivity.this, MainActivity.class);
//                    i2.putExtra("number", randNm1);
//                    startActivity(i2);
//                }
//            });
//
//            popup.show();
//
//
//        });

        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        ArrayList<User> userList = new ArrayList<>();
        userList = dbHandler.getUsers();

        if (userList.size()==0 ){
            for (int i = 0; i <20; i++)
            {
                String randName = "Name";
                String randDesc = "Description";
                boolean randFollow = false;
                int nameNum;
                int descNum;

                nameNum = new Random().nextInt(999999999);
                descNum = new Random().nextInt(999999999);

                randName = randName + " " + nameNum;
                randDesc = randDesc + " " + descNum;

                int followNum = new Random().nextInt(2);
                if (followNum == 0){
                    randFollow = true;
                }
                User u = new User(randName, randDesc, i, randFollow);
                userList.add(u);
                dbHandler.addUser(u);
            }
        }


        RecyclerView rv = findViewById(R.id.rv);
        ListActivityAdapter adapter = new ListActivityAdapter(userList, ListActivity.this);
        LinearLayoutManager layout = new LinearLayoutManager(this);

        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);
    }


}