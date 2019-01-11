package hva.example.blackhorse.com.bucketlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hva.example.blackhorse.com.bucketlist.Model.Bucket;
import hva.example.blackhorse.com.bucketlist.ViewModel.BucketViewModel;

public class AddBucketActivity extends AppCompatActivity {

    private BucketViewModel mMainViewModel;
    private EditText title;
    private EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bucket);

        title = findViewById(R.id.edit_title);
        description = findViewById(R.id.edit_description);
        mMainViewModel = new BucketViewModel(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveForm();
            }
        });
    }

    /**
     * Check the fields of the form
     */
    private boolean checkForm() {
        if (title.getText().toString().equals("") || description.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * Save the form
     */
    private void saveForm() {
        if (this.checkForm()) {
            String titleValue = title.getText().toString();
            String descriptionValue = description.getText().toString();
            Bucket bucket = new Bucket(false, titleValue, descriptionValue);
            mMainViewModel.insert(bucket);
            redirectToMainActivity();
        } else {
            Toast.makeText(this, R.string.error_fields, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Redirect to the MainActivity class.
     */
    private void redirectToMainActivity() {
        Intent intent = new Intent(AddBucketActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
