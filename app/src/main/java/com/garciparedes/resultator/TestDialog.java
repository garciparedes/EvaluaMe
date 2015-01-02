package com.garciparedes.resultator;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.google.gson.Gson;

/**
 * Created by garciparedes on 10/12/14.
 */
public class TestDialog extends Dialog {


    private int subject;
    private final ChartFragment chartFragment;
    private Button btnCreate;


    public TestDialog(Context context, int subject, ChartFragment chartFragment) {
        // Set your theme here
        super(context);

        this.subject = subject;
        this.chartFragment = chartFragment;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_add_test);

        // Set the title
        setTitle(getContext().getString(R.string.new_score));

        setCanceledOnTouchOutside(true);

        // Set the dialog text -- this is better done in the XML
        TextView textName = (TextView)findViewById(R.id.text_view_dialog_name);
        TextView textMark = (TextView)findViewById(R.id.text_view_dialog_mark);
        TextView textValue = (TextView)findViewById(R.id.text_view_dialog_value);
        final EditText editText = (EditText) findViewById(R.id.edit_text_dialog);

        textName.setText(getContext().getString(R.string.set_name));
        textMark.setText(getContext().getString(R.string.set_score));
        textValue.setText(getContext().getString(R.string.set_value));

        final NumberPicker npMark = (NumberPicker) findViewById(R.id.number_picker_mark_dialog);
        npMark.setMaxValue(10);
        npMark.setMinValue(0);

        final NumberPicker npValue = (NumberPicker) findViewById(R.id.number_picker_value_dialog);
        npValue.setMaxValue(10);
        npValue.setMinValue(0);

        btnCreate = (Button) findViewById(R.id.button_dialog);
        btnCreate.setText(getContext().getString(R.string.create_test));
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListDB.addTest(subject, editText.getText().toString(), npMark.getValue(), npValue.getValue());

                dismiss();


                chartFragment.update();

            }
        });




    }
}