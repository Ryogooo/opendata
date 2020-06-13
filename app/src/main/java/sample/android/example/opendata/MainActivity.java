package sample.android.example.opendata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = (TextView)findViewById(R.id.text);
        //AssetManagerを使用してCSVファイルを読み込む
        AssetManager assetManager = getResources().getAssets();


        try {
            //CSVファイルを指定してInputStreamを取得
            InputStream is = assetManager.open("rain.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(is);
            //InputStreamReaderをBufferedReaderを使用し、1行ずつ読込み
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line="";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null){
                StringTokenizer stringTokenizer = new StringTokenizer(line,",");
                //続きがあるかどうか判断
                while (stringTokenizer.hasMoreTokens()){
                    stringBuilder.append(stringTokenizer.nextToken());
                    stringBuilder.append(",");
                }
                //改行
                stringBuilder.append("\n");
            }
            //TextViewに表示
            text.setText(stringBuilder.toString());
            bufferedReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
