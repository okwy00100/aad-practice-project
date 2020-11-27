package com.okwy.practiceproject.AndroidArchitecture.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.okwy.practiceproject.R;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.okwy.todolist.addnoteactivity.extra_id";
    public static final String EXTRA_TITLE = "com.okwy.todolist.addnoteactivity.extra_title";
    public static final String EXTRA_DESCRIPTION = "com.okwy.todolist.addnoteactivity.extra_desc";
    public static final String EXTRA_PRIORITY = "com.okwy.todolist.addnoteactivity.extra_priority";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        numberPickerPriority = findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        if(getIntent().hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTextTitle.setText(getIntent().getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(getIntent().getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(getIntent().getIntExtra(EXTRA_PRIORITY, 1));
        }else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            saveNote();
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        int priority = numberPickerPriority.getValue();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please input a title and Description", Toast.LENGTH_SHORT).show();
        } else {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(EXTRA_TITLE, title);
            resultIntent.putExtra(EXTRA_DESCRIPTION, description);
            resultIntent.putExtra(EXTRA_PRIORITY, priority);

            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if(id != -1){
                resultIntent.putExtra(EXTRA_ID, id);
            }

            setResult(RESULT_OK, resultIntent);
            finish();

        }
    }
}