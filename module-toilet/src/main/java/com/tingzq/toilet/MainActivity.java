package com.tingzq.toilet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tingzq.toilet.irregular.IrregularActivity;
import com.tingzq.toilet.irregular.IrregularVerticalActivity;
import com.tingzq.toilet.reuglar.RegularActivity;
import com.tingzq.toilet.reuglar.RegularVerticalActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNormal = findViewById(R.id.btn_normal);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegularActivity.class);
                startActivity(intent);
            }
        });

        Button btnModule = findViewById(R.id.btn_module);
        btnModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IrregularActivity.class);
                startActivity(intent);
            }
        });

        Button btnModuleV = findViewById(R.id.btn_module_vertical);
        btnModuleV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegularVerticalActivity.class);
                startActivity(intent);
            }
        });

        Button btnModuleV1 = findViewById(R.id.btn_regular_vertical);
        btnModuleV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IrregularVerticalActivity.class);
                startActivity(intent);
            }
        });
    }
}
