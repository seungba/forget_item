package com.example.dltmd.forget_item;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final String[] LIST_MENU = {"LIST1", "LIST2", "LIST3"};
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 빈 데이터 리스트 생성
        final ArrayList<String> items = new ArrayList<String>();
        // ArrayAdapter 생성. 아이템 View를 선택(Single choice)가능하도록 만듦
        // context : 안드로이드 시스템에서 제공되는 어플리케이션 전역 환경 정보에 대한 인터페이스.
        // resource: View로 매핑될 Resource Id. "android.R.layout.simple_list_item_1"은 TextView 위젯으로 구성된 ListView 아이템 리소스 Id.
        // objects : 배열로 선언된 사용자 데이터.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, LIST_MENU);
        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String strText = (String) parent.getItemAtPosition(position);
            }
        });

        // weather button에 대한 이벤트 처리.
        Button weatherButton = (Button)findViewById(R.id.weather) ;
        weatherButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

            }
        }) ;

        // location button에 대한 이벤트 처리.
        Button locationButton = (Button)findViewById(R.id.location) ;
        locationButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){

            }
        }) ;

        // date button 에 대한 이벤트 처리.
        Button dateButton = (Button)findViewById(R.id.date);
        dateButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
               init();
            }
        }) ;
    }

    private void init(){
        final Calendar cal = Calendar.getInstance();

        Log.e(TAG, cal.get(Calendar.YEAR)+"");
        Log.e(TAG, cal.get(Calendar.MONTH)+1+"");
        Log.e(TAG, cal.get(Calendar.DATE)+"");
        Log.e(TAG, cal.get(Calendar.HOUR_OF_DAY)+"");
        Log.e(TAG, cal.get(Calendar.MINUTE)+"");

        //DATE PICKER DIALOG
        findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        String msg = String.format("%d 년 %d 월 %d 일", year, month+1, date);
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
                dialog.show();

            }
        });

    }
}
