package com.example.basegl;

import com.example.basegl.entity.CubeColor;
import com.example.basegl.entity.CubeColorLine;
import com.example.basegl.entity.CubeColorPoint;
import com.example.basegl.entity.CubeLightTexture;
import com.example.basegl.entity.CubeLine;
import com.example.basegl.entity.CubeMVPTexture;
import com.example.basegl.entity.CubePoint;
import com.example.basegl.entity.CubeTiLightTexture;
import com.example.basegl.entity.CubeWithTexture;
import com.example.basegl.entity.FBOTexture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends Activity {
    ListView listView;
    ArrayAdapter<String> adapter;
    SparseArray<Class> mapEntitys;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.list_show);
        initData();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("class_name", mapEntitys.get(position).getName());
                startActivity(intent);
            }
        });
    }
    void initData() {
        mapEntitys = new SparseArray<Class>();
        int pos = 0;
        mapEntitys.put(pos++, CubePoint.class);
        mapEntitys.put(pos++, CubeLine.class);
        mapEntitys.put(pos++, CubeColorPoint.class);
        mapEntitys.put(pos++, CubeColorLine.class);
        mapEntitys.put(pos++, CubeColor.class);
        mapEntitys.put(pos++, CubeWithTexture.class);
        mapEntitys.put(pos++, CubeMVPTexture.class);
        mapEntitys.put(pos++, CubeLightTexture.class);
        mapEntitys.put(pos++, CubeTiLightTexture.class);
        mapEntitys.put(pos++, FBOTexture.class);
        String datas[] = new String[pos];
        for (int i = 0; i < pos; i++) {
            datas[i] = (mapEntitys.get(i).getSimpleName());
        }
        adapter = new ArrayAdapter<String>(this, R.layout.list_itme, datas);
    }
}
