package sg.np.edu.mad.exercise2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListActivityViewHolder extends RecyclerView.ViewHolder {
    TextView randName;
    TextView randDesc;
    ImageView profile;

    public ListActivityViewHolder (View viewItem){
        super(viewItem);
        randName = viewItem.findViewById(R.id.randName);
        randDesc = viewItem.findViewById(R.id.randDesc);
        profile = viewItem.findViewById(R.id.profile);
    }
}
