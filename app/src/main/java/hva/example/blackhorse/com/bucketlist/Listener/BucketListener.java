package hva.example.blackhorse.com.bucketlist.Listener;

public interface BucketListener {

    /*
     * @param position
     * @param isChecked
     */
    void onBucketCheckBoxChanged(int position, boolean checked);
}