package index1.developer.nik.com.taxcalculator;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;

import index1.developer.niklocal.com.taxcalculator.R;

public class GST extends AppCompatActivity {

    ListView lv;
    EditText ed;

    TextView tx1;
    AlertDialog.Builder alert;

    DatabaseHelper db;

    AlertDialog dialog;

    ArrayList<String> il;
    ArrayList<String> tlist;
    static int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst);

        lv = (ListView) findViewById(R.id.listview);
        tx1=(TextView)findViewById(R.id.textview2);
        alert = new AlertDialog.Builder(this);
        getid();
        oncreateShow();

        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, il));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                ed = new EditText(GST.this);
                alert.setTitle("Enter Amount");
                alert.setMessage("Amount of " + il.get(i) + "\t");
                alert.setView(ed);
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                                function();
                                show();

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        function();
                        show();

                    }
                });
                dialog = alert.create();
                dialog.show();
            }
        });
    }
/*
    @Override
    public boolean releaseInstance() {
        return super.releaseInstance();
    }
*/
/*
    @Override
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, int flags) {
        return super.registerReceiver(receiver, filter, flags);
    }
*/
/*
    @Override
    protected void onRestart() {
        super.onRestart();
    }
*/
public void Display(){
    String a=getCallingPackage();
    Toast.makeText(GST.this,"Display",Toast.LENGTH_SHORT).show();

}


    public void getid() {
        db = new DatabaseHelper(this);
        try {

            db.createDatabase();
            db.openDataBase();

        } catch (Exception e) {
            e.printStackTrace();
        }
        int i, j;
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM gst", null);
        i=cursor.getColumnIndex("items");
        j=cursor.getColumnIndex("Tax%");
        il=new ArrayList<>();
        tlist=new ArrayList<>();
        while(cursor.moveToNext())
        {
            String it=cursor.getString(i);
            String tax=cursor.getString(j);
            il.add(it);
            tlist.add(tax);
        }
        cursor.close();
    }
    public void show(){
        Toast.makeText(GST.this,"Show",Toast.LENGTH_SHORT).show();
    }


    public void oncreateShow(){
        Toast.makeText(GST.this,"start",Toast.LENGTH_SHORT).show();
    }

    public void function() {
        int am = 0;
        float t;
        String val = lv.getItemAtPosition(position).toString();;
        int total = 0, tax = 0;
        if (!TextUtils.isEmpty(ed.getText().toString())) {
            am = Integer.parseInt(ed.getText().toString());
            if (val.equals(il.get(position))) {
                t=Float.parseFloat(tlist.get(position));
                tax = (int) ((am * t) / 100);
                total = am + tax;
                tx1.setText("Amount of "+val+" ="+am+"\n"+
                            "Tax% ="+t+"\n"+
                            "GST Calculated =" + tax + "\n" +
                            "Total Amount (Inclusion of GST) =" + total + "\t");
            }
        } else {
            tx1.setText("Amount of "+val+" ="+am+"\n"+
                            "Tax =" + tax + "\n" +
                        "Total amount =" + total);
        }
    }

   }

