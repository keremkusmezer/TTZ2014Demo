/**
 * TTIKUSMEZER Copyright Turkcell Teknoloji 2014
 */
package com.turkcell.androidnew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.com.turkcell.androidnew.R;

public class ScheduleJobActivity extends Activity {
	public ScheduleJobActivity() {
	}

	private Button btnSchedule;
	private Button btnStartLockTask;
	private Button btnStopLockTask;
	private Button btnRecyclerView;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.joblist);
		
		findViewById(R.id.btnPdfView).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ScheduleJobActivity.this, PdfViewerActivity.class);
				startActivity(intent);
			}
		});
		
		findViewById(R.id.btnMediaProjection).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ScheduleJobActivity.this, MediaProjectionDemo.class);
				startActivity(intent);
			}
		}); 
		
		btnRecyclerView = (Button) findViewById(R.id.btnRecyclerView);		
		btnRecyclerView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ScheduleJobActivity.this, RecyclerViewActivity.class);
				startActivity(intent);
			}
		});
		
		btnSchedule = (Button) findViewById(R.id.btnschedule);
		btnSchedule.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				JobSchedulerSample sample = new JobSchedulerSample(ScheduleJobActivity.this);
				sample.schedule();
			}
		});

		btnStartLockTask = (Button) findViewById(R.id.btnStartLockTask);
		btnStartLockTask.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startLockTask();
			}
		});
		
		btnStopLockTask = (Button) findViewById(R.id.btnStopLockTask);
		btnStopLockTask.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopLockTask();
			}
		});
	}
}