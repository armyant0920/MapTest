package com.example.map_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int WALL,FLOOR;
    Random rnd=new Random();
    Button btn_map;
    int mapSet[][];
    int X,Y;
    int CS=100;//設定格子大小
    DrawView mapView;
    LinearLayout mapLayout;

    Paint p = new Paint();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btn_map=(Button)findViewById(R.id.btn_map);
        btn_map.setOnClickListener(btnListener);
        mapLayout = (LinearLayout) findViewById(R.id.Map);
        mapView=new MainActivity.DrawView(MainActivity.this);
        //先隨便設寬高 確定塞得下就好 有特殊需求可以自己配合
        mapView.setMinimumHeight(2000);
        mapView.setMinimumWidth(2000);
        mapLayout.addView(mapView);
        //通知view组件重绘
        mapView.invalidate();

    }

    public class DrawView extends View {

        public DrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if (mapSet!=null) {
                for (int i = 0; i <= mapSet.length - 1; i++) {
                    for (int j = 0; j <= mapSet[i].length - 1; j++) {
                        if (mapSet[i][j] == 0) {
                            p.setColor(FLOOR);

                        } else {
                            p.setColor(WALL);
                        }


                        canvas.drawRect(i * CS, j * CS, (i + 1) * CS, (j + 1) * CS, p);// 正方形
                    }
                }
            }
        }
    }

    private View.OnClickListener btnListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.btn_map:
                    X=4+rnd.nextInt(5);
                    Y=4+rnd.nextInt(5);
                    Log.d("X=",String.valueOf(X));
                    Log.d("Y=",String.valueOf(Y));
                    WALL= Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    FLOOR=Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    Log.d("WALL=",String.valueOf(WALL));
                    Log.d("FLOOR=",String.valueOf(FLOOR));
                    mapSet=new int[X][Y];


                    for(int i=0;i<=mapSet.length-1;i++){
                        for(int j=0;j<=mapSet[i].length-1;j++){
                            mapSet[i][j]=rnd.nextInt(2);
                            Log.d("mapValue:","i["+i+"]"+"j["+j+"]:"+String.valueOf(mapSet[i][j]));

                        }
                    }
                    mapView.invalidate();

                    break;
            }
        }
    };
}
