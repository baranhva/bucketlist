package hva.example.blackhorse.com.bucketlist.Adapter;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import hva.example.blackhorse.com.bucketlist.Listener.BucketListener;
import hva.example.blackhorse.com.bucketlist.Model.Bucket;
import hva.example.blackhorse.com.bucketlist.R;

public class BucketAdapter extends RecyclerView.Adapter<BucketAdapter.BucketViewHolder> {

    private List<Bucket> mListBucket;
    private final BucketListener mBucketListener;

    public BucketAdapter(List<Bucket> listBucket, BucketListener mBucketListener) {
        this.mListBucket = listBucket;
        this.mBucketListener = mBucketListener;
    }

    @Override
    public BucketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()
        ).inflate(
                R.layout.bucket_layout,
                parent,
                false
        );
        return new BucketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BucketViewHolder holder, int position) {
        // Gets a single item in the list from its position
        final Bucket bucket = mListBucket.get(position);
        // The holder argument is used to reference the views inside the viewHolder
        // Populate the views with the data from the list
        holder.bucketCheckBox.setChecked(bucket.isChecked());
        holder.title.setText(bucket.getTitle());
        holder.description.setText(bucket.getDescription());
        crossOutTextIfChecked(holder.bucketCheckBox, holder.title, holder.description);
    }

    @Override
    public int getItemCount() {
        return mListBucket.size();
    }

    /**
     * Swap list of buckets with a new list.
     * Refresh the Recyclerview when new data has been added to the list.
     *
     * @param bucket
     */
    public void swapList(List<Bucket> bucket) {
        mListBucket = bucket;
        if (bucket != null) {
            this.notifyDataSetChanged();
        }
    }

    /**
     * Cross out the title and description when CheckBox is checked.
     *
     * @param checkBox
     * @param title
     * @param description
     */
    public void crossOutTextIfChecked(CheckBox checkBox, TextView title, TextView description) {
        if (checkBox.isChecked()) {
            title.setPaintFlags(title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            description.setPaintFlags(description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            title.setPaintFlags(title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            description.setPaintFlags(description.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }
    }

    public class BucketViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public CheckBox bucketCheckBox;
        public TextView title;
        public TextView description;

        public BucketViewHolder(View itemView) {
            super(itemView);
            this.bucketCheckBox = itemView.findViewById(R.id.checkbox_bucket);
            this.title = itemView.findViewById(R.id.text_title);
            this.description = itemView.findViewById(R.id.text_description);
            this.view = itemView;

            bucketCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int clickedPosition = getAdapterPosition();
                    mBucketListener.onBucketCheckBoxChanged(
                            clickedPosition,
                            bucketCheckBox.isChecked()
                    );
                    crossOutTextIfChecked(bucketCheckBox, title, description);
                }
            });
        }
    }
}
