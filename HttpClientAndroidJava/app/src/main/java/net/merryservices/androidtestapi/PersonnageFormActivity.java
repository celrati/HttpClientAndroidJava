package net.merryservices.androidtestapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.merryservices.androidtestapi.model.Personnage;
import net.merryservices.androidtestapi.services.ServerApi;

public class PersonnageFormActivity extends Activity implements View.OnClickListener {


    private EditText editTextName, editTextImage, editTextDescription;
    private boolean create=true;
    private int currentId= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnageform);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextImage = (EditText) findViewById(R.id.editTextImage);
        editTextDescription= (EditText) findViewById(R.id.editTextDescription);
        Button button= (Button) findViewById(R.id.buttonValidForm);
        button.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent= getIntent();
        Personnage personnage= (Personnage) intent.getSerializableExtra("personnage");
        if(personnage!=null) {
            editTextName.setText(personnage.getName());
            editTextImage.setText(personnage.getImage());
            editTextDescription.setText(personnage.getDescription());
            currentId= personnage.getId();
            create= false;
        }else{
            create= true;
        }
    }


    @Override
    public void onClick(View v) {
        Personnage personnage = new Personnage(currentId, editTextName.getText().toString(),
                                                editTextImage.getText().toString(),
                                                editTextDescription.getText().toString());
        if(create){
            ServerApi.createPersonnage(this, personnage);
        }else{
            ServerApi.updatePersonnage(this, personnage);
        }
    }
}