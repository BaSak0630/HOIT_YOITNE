package ga.kimdh.testfirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BusAdapter extends  RecyclerView.Adapter<BusAdapter.BusVH> {

    private Context context;

    ArrayList<Bus> list = new ArrayList<>();

    public BusAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BusVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent,false);


        return new BusVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusVH holder, int position) {

        Bus bus = list.get(holder.getbindingAdapterPostion());

        holder.busText.setText(Bus.getbus_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BusVH extends RecyclerView.ViewHolder{

        TextView busText;

        CardView cardView;

        public BusVH(@NonNull View itemView) {
            super(itemView);

            busText = itemView.findViewById(R.id.bus_text);

            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
