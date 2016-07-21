package cz.mira.allinoneapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Mira on 21.7.2016.
 */
public class PowerCalculatorFragment extends Fragment {

    private TextView tvKW, tvHP;
    private EditText etKW, etHP;
    private Button btnKW, btnHP;

    public static PowerCalculatorFragment newInstance() {
        PowerCalculatorFragment powerCalculator = new PowerCalculatorFragment();
        return powerCalculator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_power_calculator, container, false);

        tvKW = (TextView) view.findViewById(R.id.tvKW);
        tvKW.setText("Fill in KW value!");
        tvHP = (TextView) view.findViewById(R.id.tvHorse);
        tvHP.setText("Fill in HP value!");

        etKW = (EditText) view.findViewById(R.id.etKW);
        etHP = (EditText) view.findViewById(R.id.etHorse);

        btnKW = (Button) view.findViewById(R.id.btnKW);
        btnKW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(String.valueOf(etKW.getText())) > 0 && Integer.parseInt(String.valueOf(etKW.getText())) < 2000) {
                    tvKW.setText(Integer.toString((int) (Integer.parseInt(String.valueOf(etKW.getText())) * 1.341)) + " HP");
                } else {
                    tvKW.setText("Write value from 0 to 2000 KW!");
                }
            }
        });
        btnHP = (Button) view.findViewById(R.id.btnHorse);
        btnHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(String.valueOf(etHP.getText())) > 0 && Integer.parseInt(String.valueOf(etHP.getText())) < 2000) {
                    tvHP.setText(Integer.toString((int) (Integer.parseInt(String.valueOf(etHP.getText())) / 1.341)) + " KW");
                } else {
                    tvKW.setText("Write value from 0 to 2000 HP!");
                }
            }
        });

        return view;
    }
}
