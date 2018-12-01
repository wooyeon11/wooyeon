package pyneer.full_time_wannabe.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import pyneer.full_time_wannabe.R;

import butterknife.BindView;

public class MainFragment extends Fragment{
    ViewGroup rootView;
    RelativeLayout layoutBase;
    RadarChart mRadarChart;
    TextView textInfo, textPlan;
    ListView listUser;
    ArrayList<IRadarDataSet> setsRadar = new ArrayList<IRadarDataSet>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) return rootView;
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        mRadarChart = (RadarChart) rootView.findViewById(R.id.main_radar_chart);
        textInfo = (TextView) rootView.findViewById(R.id.main_text_my_info);
        textPlan = (TextView) rootView.findViewById(R.id.main_plan_textview);
        layoutBase = (RelativeLayout) rootView.findViewById(R.id.main_frag_base_layout);

        setmRadarChart();

        return rootView;
    }

    private void updateChartData() {

        YAxis yAxis = mRadarChart.getYAxis();

        yAxis.resetAxisMaximum();
        mRadarChart.animateY(1000);
        mRadarChart.invalidate();
    }

    private void setChartData(List<Integer> intList) {
        String userName = "";
        int[] recordSums = new int[3];
        ArrayList<RadarEntry> entry = new ArrayList<>();

        int i = 0;
        for (Integer record : intList) {
            recordSums[i/3] += i++;
        }

        for (int num : recordSums) {
            entry.add(new RadarEntry(num));
        }

        RadarDataSet set = new RadarDataSet(entry, userName);

        set.setColor(ColorTemplate.COLORFUL_COLORS[0]);
        set.setFillColor(ColorTemplate.COLORFUL_COLORS[0]);

        set.setDrawFilled(true);
        set.setFillAlpha(180);
        set.setLineWidth(2f);
        set.setDrawHighlightCircleEnabled(true);
        set.setDrawHighlightIndicators(false);

        setsRadar.add(set);
        RadarData data = new RadarData(setsRadar);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        mRadarChart.setData(data);

    }

    int lastSelected = 0;

    private void setmRadarChart() {
        mRadarChart.getDescription().setEnabled(false);

        mRadarChart.setTouchEnabled(false);
        mRadarChart.setWebColor(Color.GRAY);
        mRadarChart.setWebLineWidthInner(1f);
        mRadarChart.setWebColorInner(Color.GRAY);
        mRadarChart.setWebAlpha(100);

        List<Integer> sample = new ArrayList<Integer>();
        sample.add(100); sample.add(200); sample.add(300);
        setChartData(sample);

        Typeface mTfLight = Typeface.create("sans-serif-light", Typeface.NORMAL);
        XAxis xAxis = mRadarChart.getXAxis();
        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(14f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] mActivities = new String[]{"범례1", "범례2", "범례3"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mActivities[(int) value % mActivities.length];
            }
        });
        xAxis.setTextColor(Color.BLACK);

        YAxis yAxis = mRadarChart.getYAxis();
        yAxis.setTextColor(Color.BLACK);
        yAxis.setTypeface(mTfLight);
        yAxis.setLabelCount(4, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.resetAxisMaximum();
        yAxis.setDrawLabels(false);

        Legend l = mRadarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setTypeface(mTfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLACK);
    }

}