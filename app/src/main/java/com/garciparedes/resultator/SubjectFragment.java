package com.garciparedes.resultator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;


/**
 * Created by garciparedes on 5/12/14.
 */
public class SubjectFragment extends Fragment {

    private PieChart mChart;
    private PieDataSet yVals;
    private PieData data;
    private ArrayList<String> xVals;

    private TestListAdapter listAdapter;
    private ArrayList<Test> datos;
    private Subject subject;
    public static int subjectNum;

    private TextView textSubjectName;
    private View v;
    private FloatingActionButton button;

    public static SubjectFragment newInstance(int i) {
        SubjectFragment f = new SubjectFragment();
        Bundle args = new Bundle();
        args.putInt("subject", i);
        f.setArguments(args);

        return f;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subject, container, false);

    }

    /*
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


        subjectNum = getArguments().getInt("subject", 0);

        try {


            subject = ListDB.getMasterList().get(subjectNum);

            datos = subject.getTestList();

            createList(datos);

            createChart();

            createValues();
            update();

            button = (FloatingActionButton) getActivity().findViewById(R.id.fab);

            //button.attachToListView(list);


            button.bringToFront();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, FragmentAddTest.newInstance(subjectNum)).commit();

                }
            });

        }catch (IndexOutOfBoundsException e){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new DefaultFragment()).commit();
        }


    }

    private void createChart(){

        mChart = (PieChart) getView().findViewById(R.id.chart);

        mChart.setDrawLegend(false);
        mChart.setCenterTextSize(22f);

        mChart.setDescription("");
        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);
    }

    private void introduce(Test test, int i){

        xVals.add(test.getName());
        float f = test.getMark()*test.getPercentage();
        float t = (10-test.getMark())*test.getPercentage();

        data.addEntry(new Entry(f,i), 0);
        data.addEntry(new Entry(t,i),1);

    }

    private void createList(ArrayList<Test> datos){
        list = (ListView)getView().findViewById(R.id.test_listView);


        v = View.inflate(getActivity(),R.layout.fragment_chart,null);
        list.addHeaderView(v);


        listAdapter = new TestListAdapter(this, datos);
        list.setAdapter(listAdapter);

        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode,
                                                  int position, long id, boolean checked) {
                // Capture total checked items
                final int checkedCount = list.getCheckedItemCount();
                // Set the CAB title according to total checked items
                mode.setTitle(checkedCount + " Selected");
                // Calls toggleSelection method from ListViewAdapter Class
                listAdapter.toggleSelection(position);
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        // Calls getSelectedIds method from ListViewAdapter Class
                        SparseBooleanArray selected = listAdapter
                                .getSelectedIds();
                        System.out.println(selected.toString());

                        // Captures all selected ids with a loop
                        for (int i = (selected.size() - list.getHeaderViewsCount()); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Test selecteditem = listAdapter.getItem(selected.keyAt(i)-1);
                                // Remove selected items following the ids
                                subject.removeTest(selecteditem);
                                ListDB.saveData(getActivity());

                            }
                        }
                        update();
                        // Close CAB
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.folder, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
                listAdapter.removeSelection();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                return false;
            }
        });
    }

    public void update(){

        xVals = new ArrayList<String>();
        yVals = new PieDataSet(null, "Company 1");

        int[] rainbow = getResources().getIntArray(R.array.rainbow);
        yVals.setColors(rainbow);

        data = new PieData(xVals,yVals);

        mChart.setData(data);

        for (int j = 0 ; j< datos.size() ; j++){
            introduce(datos.get(j),j);
        }

        subject = ListDB.getMasterList().get(getArguments().getInt("subject", 0));
        datos = subject.getTestList();

        list.setAdapter(new TestListAdapter(this, datos));

        mChart.setData(data);
        mChart.animateXY(1500, 1500);

        v.invalidate();

    }

    public void createValues(){
        textSubjectName = (TextView) getActivity().findViewById(R.id.text_view_subject_name);
        textSubjectName.setText(subject.getName());

    }

    */
}