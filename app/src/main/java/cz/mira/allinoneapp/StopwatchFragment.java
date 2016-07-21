package cz.mira.allinoneapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Mira on 21.7.2016.
 */
public class StopwatchFragment extends Fragment {

    private TextView tvTimer;
    private Button btnStart, btnStop, btnReset;

    private long startTime = 0L;

    private Handler customHandler = new Handler();

    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    public static StopwatchFragment newInstance() {
        StopwatchFragment stopwatch = new StopwatchFragment();
        return stopwatch;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        tvTimer = (TextView) view.findViewById(R.id.tvTime);

        btnStart = (Button) view.findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);
                btnReset.setVisibility(View.INVISIBLE);
            }
        });

        btnStop = (Button) view.findViewById(R.id.btnReset);
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);
                btnReset.setVisibility(View.VISIBLE);
            }
        });

        btnReset = (Button) view.findViewById(R.id.btnStop);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = 0L;
                timeInMilliseconds = 0L;
                timeSwapBuff = 0L;
                updatedTime = 0L;
                tvTimer.setText("00 : 00 : 00");
            }
        });

        return view;
    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            tvTimer.setText("" + mins + " : " +
                    String.format("%02d", secs) + " : " +
                    String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };
}
