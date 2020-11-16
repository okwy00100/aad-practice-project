package com.okwy.practiceproject.MenusWithPickers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.okwy.practiceproject.R;

public class MenusWithPickersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus_with_pickers);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menupicker_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.order:
                Toast.makeText(this, "Order was selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.status:
                Toast.makeText(this, "Status was selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.favorites:
                Toast.makeText(this, "Favorites was selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.contacts:
                Toast.makeText(this, "Contacts was selected", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlert(View view) {
       new AlertDialog.Builder(this)
               .setTitle("Alert")
               .setMessage("Press OK to continue or Cancel to stop")
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(MenusWithPickersActivity.this, "User chose to continue", Toast.LENGTH_SHORT).show();
                   }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(MenusWithPickersActivity.this, "User chose to STOP!!", Toast.LENGTH_SHORT).show();
                   }
               })
               .show();

    }

    public void pickDate(View view) {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "DateFragment");
    }


    public void processDatePickerResult(int day, int month, int year){
        String day_string = String.valueOf(day);
        String month_string = String.valueOf(month + 1);
        String year_string = String.valueOf(year);

        Toast.makeText(this, "Date: " + day_string + "/" + month_string + "/" + year_string, Toast.LENGTH_LONG).show();
    }

}