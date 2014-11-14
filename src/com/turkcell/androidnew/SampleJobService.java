/**
 * TTIKUSMEZER Copyright Turkcell Teknoloji 2014
 */
package com.turkcell.androidnew;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

/**
 * @author TTIKUSMEZER
 * 
 */
public class SampleJobService extends JobService {
	public SampleJobService() {
	}

	// Uses Synch callbacks here, so AsyncTask or Other Method Of Offloading
	// Should Be Done Here
	@Override
	public boolean onStartJob(JobParameters params) {
		Toast.makeText(this, "Job Id called" + params.getJobId(), Toast.LENGTH_SHORT).show();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		jobFinished(params, true);
		return true;
	}

	@Override
	public boolean onStopJob(JobParameters params) {
		Toast.makeText(this, "Job Id stopped", Toast.LENGTH_SHORT).show();
		return false;
	}
}