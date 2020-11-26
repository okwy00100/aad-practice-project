package com.okwy.practiceproject.WorkManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.okwy.practiceproject.R;

public class WorkManagerActivity extends AppCompatActivity {

    private TextView workStatus;
    private OneTimeWorkRequest request;
    public static final String KEY_INPUT_DESC = "key_input_desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);

        Data data = new Data.Builder()
                .putString(KEY_INPUT_DESC, "Putting in the data for the work")
                .build();

        request = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data)
                .build();

        workStatus = findViewById(R.id.workStatus);

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                if(workInfo != null) {
                    if(workInfo.getState().isFinished()) {
                        workStatus.append(workInfo.getOutputData().getString(MyWorker.KEY_OUTPUT_DESC) + "\n");
                    }

                    String status = workInfo.getState().name();
                    workStatus.append(status + "\n");
                }

            }
        });

    }

    public void performWork(View view) {
        WorkManager.getInstance(this).enqueue(request);
    }
}