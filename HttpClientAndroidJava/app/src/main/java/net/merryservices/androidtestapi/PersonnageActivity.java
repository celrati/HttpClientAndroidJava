package net.merryservices.androidtestapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.merryservices.androidtestapi.model.Personnage;
import net.merryservices.androidtestapi.services.ServerApi;

public class PersonnageActivity extends Activity {

    private TextView textName, textDescription;
    private ImageView imageView;
    private Button buttonEdit, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnage);

        textName= (TextView) findViewById(R.id.textViewName);
        imageView= (ImageView) findViewById(R.id.imageViewAvatar);
        textDescription= (TextView) findViewById(R.id.textViewDescription);
        buttonEdit= (Button) findViewById(R.id.buttonEditPerso);
        buttonDelete= (Button) findViewById(R.id.buttonDelete);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent= getIntent();
        final Personnage personnage= (Personnage) intent.getSerializableExtra("personnage");
        textName.setText(personnage.getName());
        textDescription.setText(personnage.getDescription());
        ServerApi.loadImage(this,personnage.getImage(),imageView);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PersonnageFormActivity.class);
                intent.putExtra("personnage", personnage);
                startActivity(intent);
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerApi.deletePersonnage(getBaseContext(), personnage.getId());
            }
        });
    }
}
