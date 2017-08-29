package com.example.android.galladda.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.galladda.Activities.GameActivity;
import com.example.android.galladda.Activities.InstructionActivity;
import com.example.android.galladda.R;

/**
 * @author Belal Taher
 * Created on 8/24/2017.
 * The MainActivity class is the first activity loaded when the app starts. It lets the user either start the game or read the instructions.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * This method is called on creation of the activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Sets the view to the activity_title.xml file in the layout resources folder
        setContentView(R.layout.activity_title);

        //Makes the "Instructions" button go to the first instructions screen
        Button instructionButton =  (Button) findViewById(R.id.instruction_button);
        instructionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), InstructionActivity.class);
                startActivity(i);
            }
        });

        //Makes the "Start" button go to the game
        Button startButton =  (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext() , GameActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void onResume(){
        super.onResume();
    }

    public void onPause(){
        super.onPause();
    }

}
