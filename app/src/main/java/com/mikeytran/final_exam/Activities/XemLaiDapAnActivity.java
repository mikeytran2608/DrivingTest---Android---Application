package com.mikeytran.final_exam.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.mikeytran.final_exam.Adapter.AdapterRecyclerViewLichSuBaiThi;
import com.mikeytran.final_exam.Adapter.AdapterRecyclerViewXemDapAn;
import com.mikeytran.final_exam.DAO.CauHoiDAO;
import com.mikeytran.final_exam.Object.CauHoi;
import com.mikeytran.final_exam.R;
import java.util.ArrayList;
import java.util.List;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import static com.mikeytran.final_exam.Activities.ThiSatHachActivity.SIZE;
import static com.mikeytran.final_exam.Activities.ThiSatHachActivity.checkDungSai;



public class XemLaiDapAnActivity extends AppCompatActivity {
    RecyclerView rcv_xemDapAn;
    CauHoiDAO cauHoiDAO;
    public static List<CauHoi> list;
    List<CauHoi> listCauHoi = new ArrayList<CauHoi>();
    AdapterRecyclerViewXemDapAn adapterRecyclerViewXemDapAn;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemlaidapan);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        rcv_xemDapAn = findViewById(R.id.rcv_xemDapAn);
        cauHoiDAO = new CauHoiDAO(this);
        listCauHoi = cauHoiDAO.getListCauHoi();
        list = new ArrayList<>();
        if (getIntent().getCharExtra("from",' ')=='l') {
            list = AdapterRecyclerViewLichSuBaiThi.getListCauHoi();
            adapterRecyclerViewXemDapAn = new AdapterRecyclerViewXemDapAn(this,list,checkDungSai,'l');
        }
        else {
            for (int i=0;i<SIZE;i++){
                this.list.add(ThiSatHachActivity.list.get(i));
            }
            adapterRecyclerViewXemDapAn = new AdapterRecyclerViewXemDapAn(this,list,checkDungSai,'t');
        }
        rcv_xemDapAn.setAdapter(adapterRecyclerViewXemDapAn);
        rcv_xemDapAn.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        OverScrollDecoratorHelper.setUpOverScroll(rcv_xemDapAn, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        RecyclerViewHeader header = findViewById(R.id.header);
        header.attachTo(rcv_xemDapAn);
    }
}
