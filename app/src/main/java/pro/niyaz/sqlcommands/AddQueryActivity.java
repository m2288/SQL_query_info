package pro.niyaz.sqlcommands;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddQueryActivity extends AppCompatActivity {

    EditText editText_add_query;
    EditText editText_add_query_info;
    Button btn_Add;
    TextView textView_add_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_query);
        editText_add_query = (findViewById(R.id.editText_add_query));
        editText_add_query_info = findViewById(R.id.editText_add_query_info);
        btn_Add = findViewById(R.id.btn_Add);
        textView_add_query = findViewById(R.id.textView_add_query);
    }

    public void onAddClick(View view) {
        String new_query = editText_add_query.getText().toString();
        String new_query_info = editText_add_query_info.getText().toString();
        Intent returnIntent = new Intent();

        returnIntent.putExtra("query", new_query);
        returnIntent.putExtra("query_info", new_query_info);

        setResult(RESULT_OK, returnIntent);

        finish();
        Toast.makeText(this, "RESULT_OK", Toast.LENGTH_SHORT).show();

    }
}