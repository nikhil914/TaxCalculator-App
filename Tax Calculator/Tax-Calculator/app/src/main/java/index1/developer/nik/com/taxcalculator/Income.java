package index1.developer.nik.com.taxcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import index1.developer.niklocal.com.taxcalculator.R;

public class Income extends AppCompatActivity {

    EditText input;
    TextView text;
    TextView show;
    Button display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        input=(EditText)findViewById(R.id.input);
        text=(TextView)findViewById(R.id.text1);
        show=(TextView)findViewById(R.id.show);
        display=(Button)findViewById(R.id.button3);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 calculate();
            }
        });
    }


    public void calculate()
    {
        int All = 0,Tax=0;
        int in= Integer.parseInt(input.getText().toString());
        if (in >= 200000 && in < 500000)
        {
            Tax= (in * 4)/100;
            All = in + Tax;
        }
        else if(in >= 500000 && in < 1000000)
        {
            Tax = (in * 10)/100;
            All = in + Tax;
        }
        else if(in >= 1000000 && in < 2000000)
        {
            Tax= (in * 15)/100;
            All = in + Tax;
        }
        else if (in >= 2000000 && in < 3000000)
        {
            Tax = (in * 20)/100;
            All = in + Tax;
        }
        else if(in >= 3000000 && in < 5000000)
        {
            Tax = (in * 25)/100;
            All = in + Tax;
        }
        else if (in >= 5000000 && in < 6500000)
        {
            Tax = (in * 30)/100;
            All = in + Tax;
        }
        else if (in >= 6500000 && in < 10000000)
        {
            Tax = (in * 35)/100;
            All = in + Tax;
        }
        else if (in >= 10000000)
        {
            Tax = (in * 40)/100;
            All = in + Tax;
        }
        show.setText("Tax on your income "+input.getText()+"= \t"+Tax+"\n \n"+
                "All Income (Inclusion of Tax) "+"= \t"+All);
    }
}
