package com.turkcell.androidnew;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;

public class JobSchedulerSample {
	private Context context;
	private JobInfo.Builder uploadInfoTask;

	public JobSchedulerSample(Context context) {
		this.context = context;
		ComponentName componentName = new ComponentName(context, SampleJobService.class);
		uploadInfoTask = new JobInfo.Builder(1928, componentName);
		uploadInfoTask.setRequiresCharging(true);
		uploadInfoTask.setRequiresDeviceIdle(false);
		uploadInfoTask.setPeriodic(200);
		uploadInfoTask.setRequiredNetworkType(JobInfo.NETWORK_TYPE_NONE);
	}

	public void schedule() {
		JobInfo sampleJobInfo = uploadInfoTask.build();
		JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
		scheduler.schedule(sampleJobInfo);
	}
}