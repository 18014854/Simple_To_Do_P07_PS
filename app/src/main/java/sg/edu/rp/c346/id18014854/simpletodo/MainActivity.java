package sg.edu.rp.c346.id18014854.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddDel;
    EditText etInput;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvTask;

    ArrayList<String> alTasks;
    ArrayAdapter<String> aaTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInput = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.buttonAdd);
        btnDelete = findViewById(R.id.buttonDelete);
        btnClear = findViewById(R.id.buttonClear);
        lvTask = findViewById(R.id.listViewTask);
        spnAddDel = findViewById(R.id.spinner);

        alTasks = new ArrayList<>();

        aaTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);

        lvTask.setAdapter(aaTasks);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etInput.getText().toString();
                alTasks.add(task);
                etInput.setText(""); //reset editTextInput (clear input)
                aaTasks.notifyDataSetChanged();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aaTasks.clear();
                etInput.setText(""); //reset editTextInput (clear input)
                aaTasks.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTasks.isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.noTask, Toast.LENGTH_LONG).show();
                } else if(Integer.parseInt(etInput.getText().toString()) >= alTasks.size()){
                    Toast.makeText(MainActivity.this, R.string.noIndex, Toast.LENGTH_LONG).show();
                } else {
                    alTasks.remove(Integer.parseInt(etInput.getText().toString()));
                    etInput.setText("");
                    aaTasks.notifyDataSetChanged();
                }
            }
        });

        spnAddDel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        etInput.setHint(R.string.inputTask);
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        etInput.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                    case 1:
                        etInput.setHint(R.string.inputRemoveTask);
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        etInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
