package room.sample.mvvm.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import room.sample.mvvm.R;
import room.sample.mvvm.model.UserModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<UserModel> userModelList;
    private View.OnLongClickListener longClickListener;

    public RecyclerViewAdapter(List<UserModel> userModelList, View.OnLongClickListener longClickListener) {
        this.userModelList = userModelList;
        this.longClickListener = longClickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        UserModel userModel = userModelList.get(position);
        holder.itemTextView.setText(userModel.getEmpId());
        holder.nameTextView.setText(userModel.getName());

        holder.itemView.setTag(userModel);
        holder.itemView.setOnLongClickListener(longClickListener);
    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    public void addItems(List<UserModel> borrowModelList) {
        this.userModelList = borrowModelList;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView itemTextView;
        private TextView nameTextView;

        RecyclerViewHolder(View view) {
            super(view);
            itemTextView = view.findViewById(R.id.itemTextView);
            nameTextView = view.findViewById(R.id.nameTextView);
        }
    }
}