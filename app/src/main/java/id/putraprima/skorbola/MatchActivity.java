package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import model.MatchData;

public class MatchActivity extends AppCompatActivity {

    public static final String DATA_KEY = "key";

    TextView tvScoreHome;
    TextView tvScoreAway;
    TextView tvHome;
    TextView tvAway;

    Uri homeUri = null;
    Uri awayUri = null;
    int scoreHome;
    int scoreAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        tvHome = findViewById(R.id.txt_home);
        tvAway = findViewById(R.id.txt_away);
        tvScoreHome = findViewById(R.id.score_home);
        tvScoreAway = findViewById(R.id.score_away);

        ImageView ivHome = findViewById(R.id.home_logo);
        ImageView ivAway = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            MatchData match = getIntent().getParcelableExtra(DATA_KEY);
            tvHome.setText(match.getHomeTeam());
            tvAway.setText(match.getAwayTeam());
            homeUri = match.getHomeUri();
            awayUri = match.getAwayUri();
            scoreHome = match.getScoreHome();
            scoreAway = match.getScoreAway();

            tvScoreHome.setText(String.valueOf(scoreHome));
            tvScoreAway.setText(String.valueOf(scoreAway));
            Bitmap bitmapHome = null;
            Bitmap bitmapAway = null;
            try{
                bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
                bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
            }catch(Exception e){
                Toast.makeText(this, "Failed to load images", Toast.LENGTH_SHORT).show();
            }
            ivHome.setImageBitmap(bitmapHome);
            ivAway.setImageBitmap(bitmapAway);
        }
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    public void addHomeScore(View view) {
        scoreHome++;
        tvScoreHome.setText(String.valueOf(scoreHome));
    }

    public void addAwayScore(View view) {
        scoreAway++;
        tvScoreAway.setText(String.valueOf(scoreAway));
    }

    public void SubmitScore(View view) {
        if(scoreHome == scoreAway){
            resultCounter("Draw");
        }else if(scoreHome < scoreAway){
            resultCounter(tvAway.getText().toString());
        }else if(scoreHome > scoreAway){
            resultCounter(tvHome.getText().toString());
        }
    }

    public void resultCounter(String winner){
        Intent intent = new Intent(this, ResultActivity.class);
        MatchData result = new MatchData(tvHome.getText().toString(), tvAway.getText().toString(), homeUri, awayUri, scoreHome, scoreAway, winner);
        intent.putExtra(DATA_KEY, result);
        startActivity(intent);
    }
}
