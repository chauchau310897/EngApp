package intonationeenglish.demo.intonationenlish;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class insertVideo extends AppCompatActivity {
    TextView tvPath;
    Button btnChose,btnPlay;
    int SELECT_VIDEO_DIALOG = 1;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_video);
        tvPath= (TextView) findViewById(R.id.tv_path);
        btnChose= (Button) findViewById(R.id.btn_browse);
        btnPlay= (Button) findViewById(R.id.btn_play);
        btnChose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_video = new Intent();
                intent_video.setType("video/*");
                intent_video.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent_video,"Select Video"),SELECT_VIDEO_DIALOG);
            }
        });

    }
}
