package com.example.android.takehomeassignment09_doraj;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView time;
    private TextView showVote;
    private EditText name;
    private EditText company;
    private EditText genre;
    private String voteName;
    private String voteCompany;
    private String voteGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        Intent intent = getIntent();

        showVote = (TextView)findViewById(R.id.show_detail);
        name = (EditText)findViewById(R.id.vote_name);
        company = (EditText)findViewById(R.id.company);
        genre = (EditText)findViewById(R.id.genre);
        time = (TextView)findViewById(R.id.time);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        time.setText("Current Date and Time : "+formattedDate);
    }

    private FirebaseDatabase voteDatabase= FirebaseDatabase.getInstance();;
    private DatabaseReference singleVoteRef = voteDatabase.getReference("Single Vote");
    private DatabaseReference multiVoteRef = voteDatabase.getReference("Multiple Votes");

    public void setSingleVote(View view){
        voteGenre = genre.getText().toString();
        voteCompany = company.getText().toString();
        voteName = name.getText().toString();

        singleVoteRef.setValue(new Game(voteName,voteCompany,voteGenre));

        singleVoteRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Game g = dataSnapshot.getValue(Game.class);
                showVote.setText(g.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error Loading FireBase", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addMultiVote(View view){
        voteGenre = genre.getText().toString();
        voteCompany = company.getText().toString();
        voteName = name.getText().toString();

        multiVoteRef.push().setValue(new Game(voteName,voteCompany,voteGenre));
    }

}
