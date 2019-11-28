package com.softeng_grup6.vainsfitness.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.softeng_grup6.vainsfitness.R;
import com.softeng_grup6.vainsfitness.managers.UserInterfaceManager;
import com.softeng_grup6.vainsfitness.systems.ClientSystem;
import com.softeng_grup6.vainsfitness.utils.Date;
import com.softeng_grup6.vainsfitness.utils.Profile;
import com.softeng_grup6.vainsfitness.utils.Report;

import java.util.Calendar;

import at.grabner.circleprogress.CircleProgressView;

public class ProgressReport extends Fragment {
    TextView weight_loss_disp;
    TextView status_disp;
    TextView total_days_disp;
    TextView avg_daily_cal_disp;
    TextView avg_daily_weight_loss_disp;
    LinearLayout status_box;

    private CircleProgressView progressView = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progressreport, container, false);
        weight_loss_disp = view.findViewById(R.id.weight_loss);
        status_disp = view.findViewById(R.id.status);
        total_days_disp = view.findViewById(R.id.days);
        avg_daily_cal_disp = view.findViewById(R.id.avg_calorie);
        avg_daily_weight_loss_disp = view.findViewById(R.id.avg_weight_loss);
        progressView = view.findViewById(R.id.progress);
        status_box = view.findViewById(R.id.status_box);
        if(UserInterfaceManager.getLoggedInUserType().equals("client")){
            clientConfig();
        }
        return view;
    }
    public void clientConfig(){
        Report profileReport = ClientSystem.getClientProfile().getProgressReport();
        float progress = Float.parseFloat(""+profileReport.getProgrssPercentage());
        progressView.setValue(progress);
        weight_loss_disp.setText(""+profileReport.getWeightLoss()+" lb");
        total_days_disp.setText(""+profileReport.getTotalDays());
        avg_daily_weight_loss_disp.setText(""+profileReport.getAvgDailyWeightLoss()+" lb");
        avg_daily_cal_disp.setText(""+profileReport.getAvgCalorieConsumption());
        status_disp.setText(""+profileReport.getStatus());
        switch ((int) profileReport.getStatus()){
            case 0:
                status_box.setBackgroundColor(Color.parseColor("#43e872"));
                status_disp.setText("Good");
                break;
            case 1:
                status_box.setBackgroundColor(Color.parseColor("#e8e043"));
                status_disp.setText("Eat a little less");
                break;
            case 2:
                status_box.setBackgroundColor(Color.parseColor("#e85443"));
                status_disp.setText("Eating too much");
                break;
            default:
                status_box.setBackgroundColor(Color.parseColor("#e84364"));
                status_disp.setText("Consult Coach Now");


        }

    }
}
