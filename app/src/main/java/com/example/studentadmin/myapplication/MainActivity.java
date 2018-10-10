package com.example.studentadmin.myapplication;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    EditText id, name, lname, mark;
    Button add, update, show, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);

        id = (EditText) findViewById(R.id.Id);
        name = (EditText) findViewById(R.id.name);
        lname = (EditText) findViewById(R.id.lname);
        mark = (EditText) findViewById(R.id.mark);
        add = (Button) findViewById(R.id.add);
        update = (Button) findViewById(R.id.update);
        show = (Button) findViewById(R.id.show);
        delete = (Button) findViewById(R.id.delete);


    }


    public void add(View view) {
        int id1 = (Integer.parseInt(id.getText().toString()));
        String name1 = name.getText().toString();
        String lastname = lname.getText().toString();
        int marks1 = Integer.parseInt(mark.getText().toString());
        Boolean isInserted = dbHelper.insertData(id1, name1, lastname, marks1);
        if (isInserted) {
            Toast.makeText(getApplicationContext(), "INSERTED", Toast.LENGTH_LONG).show();
        }

    }

    public void update(View view) {
        int id1 = (Integer.parseInt(id.getText().toString()));
        String name1 = name.getText().toString();
        String lastname = lname.getText().toString();
        int marks1 = Integer.parseInt(mark.getText().toString());
        Boolean upd = dbHelper.updateData(id1,name1,lastname,marks1);
        if(upd)
        {
            Toast.makeText(getApplicationContext(), "updated", Toast.LENGTH_LONG).show();
        }
    }

    public void show(View view) {
        Cursor cur = dbHelper.showData();
        if (cur.getCount() == 0)
        {
            //show message
            Toast.makeText(getApplicationContext(),"Insert",Toast.LENGTH_SHORT).show();
        }
        else {
            StringBuffer sb = new StringBuffer();
            while (cur.moveToNext()) {
                sb.append("ID : " + cur.getInt(0) + " \n Name : " + cur.getString(1) + "\n Surname : " + cur.getString(2) + "Marks :" + cur.getInt(3));
            }

            showMessage("Data",sb.toString());
        }

    }

    public void delete(View view) {
        Integer row =dbHelper.deleteData(Integer.parseInt(id.getText().toString()));
        if(row > 0)
        {
            Toast.makeText(getApplicationContext(),"Data Deleted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }
    }


    public void showMessage(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}