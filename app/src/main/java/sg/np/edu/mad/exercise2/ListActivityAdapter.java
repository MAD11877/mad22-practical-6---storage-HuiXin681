package sg.np.edu.mad.exercise2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivityAdapter extends RecyclerView.Adapter<ListActivityViewHolder> {
    ArrayList<User> userList;
    public ListActivityAdapter(ArrayList<User> data, ListActivity listActivity){
        this.userList = data;

    }

    @NonNull
    @Override
    public ListActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        if (viewType == 0){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv, null, false);
        }
        else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.alternate_rsv, null, false);
        }
        return new ListActivityViewHolder(item);
    }

    @Override
    public int getItemViewType(int position){
        String n = userList.get(position).name;
        if (Character.toString(n.charAt(n.length()-1)).equals("7")){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ListActivityViewHolder holder, int position) {
        String n = userList.get(position).name;
        holder.randName.setText(n);
        String d = userList.get(position).description;
        holder.randDesc.setText(d);
        boolean f = userList.get(position).followed;
        int identification = userList.get(position).Id;

        holder.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder profilePopup = new AlertDialog.Builder(view.getContext());
                profilePopup.setTitle("Profile");
                profilePopup.setMessage(n);
                profilePopup.setCancelable(false);
                profilePopup.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogueInterface, int i) {

                    }
                });
                profilePopup.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent i2 = new Intent(view.getContext(), MainActivity.class);
                        i2.putExtra("name", n);
                        i2.putExtra("desc", d);
                        i2.putExtra("followTF", f);
                        i2.putExtra("id", identification);
                        (view.getContext()).startActivity(i2);
                    }
                });
                profilePopup.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
