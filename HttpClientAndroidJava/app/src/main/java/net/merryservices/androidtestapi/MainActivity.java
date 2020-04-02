package net.merryservices.androidtestapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import net.merryservices.androidtestapi.model.Personnage;
import net.merryservices.androidtestapi.model.PersonnageAdapter;
import net.merryservices.androidtestapi.services.IListenerAPI;
import net.merryservices.androidtestapi.services.ServerApi;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IListenerAPI {

    private ListView listView;
    private PersonnageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Personnage> personnages = new ArrayList<Personnage>();
        adapter= new PersonnageAdapter(this);
        listView = (ListView) findViewById(R.id.listMain);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), PersonnageActivity.class);
                intent.putExtra("personnage", adapter.getPersonnages().get(position));
                startActivity(intent);
            }
        });

        Button button = (Button) findViewById(R.id.buttonAdd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getBaseContext(), PersonnageFormActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ServerApi.getPersonnages(this, this);
    }

    @Override
    public void receivePersonnages(ArrayList<Personnage> personnages) {
        adapter.setPersonnages(personnages);
        adapter.notifyDataSetChanged();
    }
}
