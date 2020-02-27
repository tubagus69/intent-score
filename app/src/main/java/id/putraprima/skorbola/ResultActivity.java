package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import model.MatchData;

public class ResultActivity extends AppCompatActivity {

    public static final String DATA_KEY = "key";
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvResult = findViewById(R.id.textView3);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            MatchData data = getIntent().getParcelableExtra(DATA_KEY);
            if(data.getInfo().equals("Draw")){
                tvResult.setText(data.getInfo());
            }else{
                tvResult.setText(String.format("The winner is %s", data.getInfo()));
            }
        }
    }
}
