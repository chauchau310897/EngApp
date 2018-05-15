package intonationeenglish.demo.intonationenlish;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;


public class TestActivity extends AppCompatActivity {
    TextView tvSub,tvKQ;
    Button btnPratic,btnTest,btnThuVien;
    ImageButton ibtnMic;
    static ArrayList<Test> array = new ArrayList<Test>();
    Context context;
    static Test test;
    static int ACTION_RECOGNIZE_SPEECH=1;
    static int LANGUAGE_MODEL_FREE_FORM =1;
    static int EXTRA_PROMPT  =1;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    int solan=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        context=getApplicationContext();
        btnTest= (Button) findViewById(R.id.btn_test_lai);
        tvSub= (TextView) findViewById(R.id.txt_subtitle_test);
        tvKQ= (TextView) findViewById(R.id.txt_subtitle_kq);
        ibtnMic= (ImageButton) findViewById(R.id.img_mic);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFileTxtFromAssets(context,"test.txt");
                Random r = new Random();
                int a = r.nextInt(array.size());
                tvSub.setText(array.get(a).getTest());
                array.get(a).setState(0);
            }
        });
        ibtnMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvKQ.setText(result.get(0));
                }
                break;
            }

        }

    }

    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }

    }

    public static void readFileTxtFromAssets(Context context, String fileName) {
        String strLine;int i=0;
        try{
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            InputStreamReader inStrR = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader buffreader = new BufferedReader(inStrR);
            while (( strLine = buffreader.readLine()) != null) {
                test=new Test(i,strLine,1);
                i++;
                array.add(test);
              //  strContent += strLine + “\n”;
            }
            inputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
