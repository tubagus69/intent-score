package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import model.MatchData;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "soccer";
    public static final String DATA_KEY = "key";

    EditText homeTeam;
    ImageView homeLogo;

    EditText awayTeam;
    ImageView awayLogo;

    Uri homeUri = null;
    Uri awayUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeTeam = findViewById(R.id.home_team);
        homeLogo = findViewById(R.id.home_logo);

        awayTeam = findViewById(R.id.away_team);
        awayLogo = findViewById(R.id.away_logo);


        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == 1) {
            if (data != null) {
                try {
                    homeUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), homeUri);
                    homeLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        } else if(requestCode == 2){
            if (data != null) {
                try {
                    awayUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), awayUri);
                    awayLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void SubmitTeam(View view) {
        int errorCount = 4;
        String home = homeTeam.getText().toString().trim();
        String away = awayTeam.getText().toString().trim();
        Intent intent = new Intent(this, MatchActivity.class);

        if(checkHome(home)){
            errorCount -= 1;
        }
        if(checkAway(away)){
            errorCount -= 1;
        }

        if(homeUri != null){
            errorCount -= 1;
        }else{
            SubmitHomeImage(view);
            Toast.makeText(this, "Tambahkan gambar tim "+ home +" dulu ya!", Toast.LENGTH_SHORT).show();
        }

        if(awayUri != null){
            errorCount -= 1;
        }else{
            SubmitAwayImage(view);
            Toast.makeText(this, "Tambahkan gambar tim "+ away +" dulu ya!", Toast.LENGTH_SHORT).show();
        }

        if(errorCount == 0){
            MatchData matchData = new MatchData(home, away, homeUri, awayUri, 0, 0, null);
            intent.putExtra(DATA_KEY, matchData);
            startActivity(intent);
        }

        Log.d("count", "This need"+ errorCount);

    }

    private boolean checkAway(String away) {
        if(away.isEmpty()){
            awayTeam.setError("Please fill home team name");
            return false;
        }else{
            return true;
        }
    }

    private boolean checkHome(String home) {
        if(home.isEmpty()){
            homeTeam.setError("Please fill away team name");
            return false;
        }else{
            return true;
        }
    }

    public void SubmitHomeImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void SubmitAwayImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
}
