package pro.niyaz.sqlcommands;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText = findViewById(R.id.editText);
        Button btn_Search = findViewById(R.id.btn_Search);
        Button btn_Add = findViewById(R.id.btn_Add);
        TextView textView = findViewById(R.id.textView);
    }

    public void onAddQueryClick(View view) {
        Intent intent = new Intent(this, AddQueryActivity.class);
        startActivity(intent);

    }

    public void onSearchClick(View view) {
    }
}