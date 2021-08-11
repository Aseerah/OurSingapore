package sg.edu.rp.c346.id20009530.oursingapore;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etName, etDescription, etSquare;
    Button btnCancel, btnUpdate, btnDelete;
    RatingBar rb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etSquare = (EditText) findViewById(R.id.etSquare);
        rb = (RatingBar) findViewById(R.id.ratingBar2);


        Intent i = getIntent();
        final Island currentIsland = (Island) i.getSerializableExtra("island");

        etID.setText(currentIsland.getId() + "");
        etName.setText(currentIsland.getName());
        etDescription.setText(currentIsland.getDescription());
        rb.setNumStars(currentIsland.getStars());
        etSquare.setText(currentIsland.getSquare() + "");


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                currentIsland.setName(etName.getText().toString().trim());
                currentIsland.setDescription(etDescription.getText().toString().trim());
                int area = 0;
                try {
                    area = Integer.valueOf(etSquare.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ThirdActivity.this, "Invalid area", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentIsland.setSquare(area);

                currentIsland.setStars((int) rb.getRating());
                int result = dbh.updateIsland(currentIsland);
                if (result>0){
                    Toast.makeText(ThirdActivity.this, "Island updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete this island? \n" + currentIsland.getName());
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DBHelper dbh = new DBHelper(ThirdActivity.this);
                        int result = dbh.deleteIsland(currentIsland.getId());
                        if (result > 0) {
                            Toast.makeText(ThirdActivity.this, "Island deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ThirdActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                myBuilder.setNegativeButton("NO",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}