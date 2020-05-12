package space.victori.iflefs;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import space.victori.iflefs.Array;
import space.victori.iflefs.GameLevels;
import space.victori.iflefs.R;

public class Level2 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogEnd;


    public int numLeft; //Переменная для левой картинки + текст
    public int numRight; //Переменная для правой картинки + текст
    Array array=new Array();
    Random random= new Random();
    public int count= 0; //Счётчик правильных ответов

    private Chronometer mChronometer;// секундомер

    // @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal);


        //секундомер
        mChronometer = findViewById(R.id.chronometer);
        mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime()
                        - mChronometer.getBase();
            }
        });



        //установка "Уровень1"
        TextView text_levels= findViewById(R.id.text_levels);
        text_levels.setText(R.string.level2);
        //Установка описания уровня внутре него
        TextView text_desc = findViewById(R.id.text_description_in);
        text_desc.setText(R.string.description2);

        final ImageView img_left = (ImageView) findViewById(R.id.img_left);
        //скруглить углы
        img_left.setClipToOutline(true);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        //скруглить углы
        img_right.setClipToOutline(true);

        //Путь к левой TextView
        final TextView text_left = findViewById(R.id.text_left);
        //Путь к правой TextView
        final TextView text_right = findViewById(R.id.text_right);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачное окно
        dialog.setCancelable(false);

        //Установка картинки в диалоговое окно
        ImageView previewimg= (ImageView)dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource((R.drawable.previewimgtwo));

        //Установка описания задания
        TextView textdescription = (TextView)dialog.findViewById(R.id.text_description);
        textdescription.setText(R.string.leveltwo);

        //кнопка "х"
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(space.victori.iflefs.Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
                dialog.dismiss();
            }

        });


        //кнопка "продолжить"
        Button btncontinue = (Button) dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChronometer.setBase(SystemClock.elapsedRealtime());//запускаем секундомер
                mChronometer.start();
                dialog.dismiss();
            }
        });
        dialog.show();


        //___________________________________________________________
        //Вызов диалогового окна в конце игры
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачное окно
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);


        //Факт
        TextView textdescriptionEnd = (TextView)dialogEnd.findViewById(R.id.text_descriptionEnd);
        textdescriptionEnd.setText(R.string.leveltwoend);



        //кнопка "x"
        TextView btnclose2 = (TextView) dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mChronometer.setBase(SystemClock.elapsedRealtime());//сброс секундомера
                    Intent intent = new Intent(space.victori.iflefs.Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
                dialogEnd.dismiss();
            }
        });
        //кнопка "продолжить"
        Button btncontinue2 = (Button) dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mChronometer.setBase(SystemClock.elapsedRealtime());//сброс секундомера
                    Intent intent = new Intent(space.victori.iflefs.Level2.this, Level3.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e){}

                dialogEnd.dismiss();
            }
        });
        //_________________________________________________________



        Button btn_back = (Button) findViewById(R.id.button_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(space.victori.iflefs.Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        });


        //Массив для прогресса игры
        final int[] progress={
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
                R.id.point11, R.id.point12, R.id.point13, R.id.point14, R.id.point15,
                R.id.point16, R.id.point17, R.id.point18, R.id.point19, R.id.point20,
        };

        //Анимация
        final Animation a= AnimationUtils.loadAnimation(space.victori.iflefs.Level2.this, R.anim.alpha);

        numLeft = random.nextInt(8);
        img_left.setImageResource(array.imaegs2[numLeft]);//Достаём из массива картинку
        text_left.setText(array.texts2[numLeft]);//Достаём из массива текст

        numRight = random.nextInt(8);
        //Если числа равны
        while (numLeft==numRight){
            numRight = random.nextInt(8);
        }
        img_right.setImageResource(array.imaegs2[numRight]);
        text_right.setText(array.texts2[numRight]);

        //Обработка нажатие на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);  ///Блокируем правую картинку
                    if (numLeft>numRight){
                        img_left.setImageResource(R.drawable.img_true);
                    }else {
                        img_left.setImageResource(R.drawable.img_false);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    if (numLeft>numRight){
                        if(count<20){ count++; }
                        //Закрашиваем прогресс
                        for(int i=0;i<20; i++){
                            TextView tv= findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //Если правильно- закрашиваем зеленым
                        for(int i=0;i<count; i++){
                            TextView tv= findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        for(int i=0;i<19; i++){
                            TextView tv= findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //Если правильно- закрашиваем зеленым
                        for(int i=0;i<count; i++){
                            TextView tv= findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count==20){//ВЫХОД ИЗ УРОВНЯ
                        mChronometer.stop();//остановка секундомера
                        dialogEnd.show();

                    }else {
                        numLeft = random.nextInt(8);
                        img_left.setImageResource(array.imaegs2[numLeft]);//Достаём из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts2[numLeft]);

                        numRight = random.nextInt(8);
                        //Если числа равны
                        while (numLeft==numRight){
                            numRight = random.nextInt(8);
                        }
                        img_right.setImageResource(array.imaegs2[numRight]);//Достаём из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts2[numRight]);

                        img_right.setEnabled(true); //Включаем обратно правую картинку
                    }
                }
                return true;
            }
        });

        //Обработка нажатие на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);  ///Блокируем левую картинку
                    if (numLeft<numRight){
                        img_right.setImageResource(R.drawable.img_true);
                    }else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    if (numLeft<numRight){
                        if(count<20){ count++; }
                        //Закрашиваем прогресс
                        for(int i=0;i<20; i++){
                            TextView tv= findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //Если правильно- закрашиваем зеленым
                        for(int i=0;i<count; i++){
                            TextView tv= findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }else{
                        if(count>0){
                            if(count==1){
                                count=0;
                            }else{
                                count=count-2;
                            }
                        }
                        for(int i=0;i<19; i++){
                            TextView tv= findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }

                        //Если правильно- закрашиваем зеленым
                        for(int i=0;i<count; i++){
                            TextView tv= findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                    }
                    if(count==20){ //ВЫХОД ИЗ УРОВНЯ
                        mChronometer.stop();//остановка секундомера
                        dialogEnd.show();

                    }else {
                        numLeft = random.nextInt(8);
                        img_left.setImageResource(array.imaegs2[numLeft]);//Достаём из массива картинку
                        img_left.startAnimation(a);
                        text_left.setText(array.texts2[numLeft]);

                        numRight = random.nextInt(8);
                        //Если числа равны
                        while (numLeft==numRight){
                            numRight = random.nextInt(8);
                        }
                        img_right.setImageResource(array.imaegs2[numRight]); //Достаём из массива картинку
                        img_right.startAnimation(a);
                        text_right.setText(array.texts2[numRight]); //Достаём из массива текст

                        img_left.setEnabled(true); //Включаем обратно левую картинку
                    }
                }
                return true;
            }
        });



    }
    //системная кнопка "назад"
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(space.victori.iflefs.Level2.this, GameLevels.class);
            startActivity(intent);
            finish();
        }catch (Exception e){}
    }

}