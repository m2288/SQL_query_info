package pro.niyaz.sqlcommands;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String DB_NAME = "my_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "SQL_queries_table";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "command_name";
    private static final String DESCRIPTION_COL = "description";
    SQLiteDatabase my_db;
    EditText editText;
    Button btn_Search;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        btn_Search = findViewById(R.id.btn_Search);
        textViewResult = findViewById(R.id.textViewResult);
        my_db = openOrCreateDatabase("my_db", MODE_PRIVATE, null);

        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT)";
        my_db.execSQL(query);

        ContentValues cvalues = new ContentValues();
        cvalues.put(NAME_COL, "SELECT");
        cvalues.put(DESCRIPTION_COL, "SELECTin izahı");

        my_db.insert(TABLE_NAME, null, cvalues);

        Cursor cursor;
        cursor = my_db.rawQuery("SELECT description FROM SQL_queries_table", null);
        cursor.moveToFirst();
       /* do {
            int id = cursor.getInt(cursor.getColumnIndex("description"));
        } while (cursor.moveToNext());
        cursor.close();

        DatabaseUtils.dumpCursor(cursor);
        String result = cursor.getString(cursor.getColumnIndex("command_name"));
        Log.d("lessonmsg", result);
        */

        cursor.close();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case pro.niyaz.sqlcommands.R.id.add:
                Intent intent = new Intent(this, AddQueryActivity.class);
                startActivityForResult(intent, 123);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {

            assert data != null;
            String query = data.getStringExtra("query");
            String query_info = data.getStringExtra("query_info");

            System.out.println(query);
            System.out.println(query_info);

            ContentValues c_values = new ContentValues();
            c_values.put(NAME_COL, query);
            c_values.put(DESCRIPTION_COL, query_info);

            my_db.insert(TABLE_NAME, null, c_values);

        }
    }


    public void onSearchClick(View view) {
        EditText editText = findViewById(R.id.editText);
        String search_query = editText.getText().toString();
        Cursor cursor;
        cursor = my_db.rawQuery("SELECT * FROM SQL_queries_table WHERE command_name = '" + search_query + "'", null);
        cursor.moveToFirst();
        //System.out.print(cursor.getString(cursor.getColumnIndexOrThrow("id")));
        int id;
        String command_name;
        String description;
        String result_text = "";
        while (cursor.moveToNext()) {
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            command_name = cursor.getString(cursor.getColumnIndexOrThrow("command_name"));
            description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            result_text += command_name + "\n" + id + " " + description;
        }
        cursor.close();
        if (result_text == "") {
            result_text = "Sorğu üzrə heç bir məlumat tapılmadı";
        }
        textViewResult.setText(result_text);

    }
}