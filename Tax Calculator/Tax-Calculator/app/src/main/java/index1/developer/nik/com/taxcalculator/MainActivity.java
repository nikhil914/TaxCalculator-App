package index1.developer.nik.com.taxcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import index1.developer.niklocal.com.taxcalculator.R;

public class MainActivity extends AppCompatActivity {

    Button it;
    Button gc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        it=(Button)findViewById(R.id.it);
        gc=(Button)findViewById(R.id.gc);

        it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in= new Intent(MainActivity.this,Income.class);
                startActivity(in);
            }
        });

        gc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, GST.class);
                startActivity(intent);

            }
        });
    }
}
