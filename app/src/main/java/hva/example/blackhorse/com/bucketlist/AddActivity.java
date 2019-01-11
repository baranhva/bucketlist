package hva.example.blackhorse.com.bucketlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hva.example.blackhorse.com.bucketlist.Model.Bucket;
import hva.example.blackhorse.com.bucketlist.ViewModel.BucketViewModel;

public class AddActivity extends AppCompatActivity {

    private BucketViewModel mBucketViewModel;

    @BindView(R.id.edit_title)
    EditText title;
    @BindView(R.id.edit_description)
    EditText description;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bucket);
        ButterKnife.bind(this);

        mBucketViewModel = new BucketViewModel(getApplicationContext());

        saveTheBucket();

    }

    /**
     * Save new bucket into the database.
     * This function will check whether the Edittext is not empty and also if
     * the description is not empty.
     */
    private void InsertNewBucket() {
        if (title.getText().toString().equals("") || description.getText().toString().equals("")) {
            String titleValue = title.getText().toString();
            String descriptionValue = description.getText().toString();
            Bucket bucket = new Bucket(false, titleValue, descriptionValue);
            mBucketViewModel.insert(bucket);
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_fields, Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.fab)
    public void saveTheBucket() {
        InsertNewBucket();
    }
}
