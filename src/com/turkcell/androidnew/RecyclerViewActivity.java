/**
 * TTIKUSMEZER Copyright Turkcell Teknoloji 2014
 */
package com.turkcell.androidnew;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.com.turkcell.androidnew.R;

//http://commonsware.com/blog/2014/06/28/and-now-your-l-api-change-wtfs.html
//Android now has some amount of tracking of “power save mode”, with an isPowerSaveMode() on PowerManager 
//and an ACTION_POWER_SAVE_MODE_CHANGED broadcast. Whether this is for OEM-specific modes or for some new common power save framework in Android, I cannot say.
//https://github.com/twotoasters/RecyclerViewLib/blob/master/library/src/main/java/com/twotoasters/android/support/v7/widget/RecyclerView.java
public class RecyclerViewActivity extends Activity implements RecyclerView.OnItemTouchListener, View.OnClickListener {
	// http://www.grokkingandroid.com/
	private RecyclerView recyclerView;
	private RecyclerViewAdapter recyclerViewAdapter;
	private ImageButton btnAdd;
	private ImageButton btnRemove;

	public RecyclerViewActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recyclerlistview);
		btnAdd = (ImageButton) findViewById(R.id.fab_add);
		btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				recyclerViewAdapter.addData("kerem", 0);
			}
		});
		btnRemove = (ImageButton) findViewById(R.id.fab_remove);
		btnRemove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				recyclerViewAdapter.removeData(0);
			}
		});
		recyclerView = (RecyclerView) findViewById(R.id.rvlist);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerViewAdapter = new RecyclerViewAdapter();
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		layoutManager.scrollToPosition(0);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setAdapter(recyclerViewAdapter);
		// onClickDetection is done in this Activity's onItemTouchListener
		// with the help of a GestureDetector;
		// Tip by Ian Lake on G+ in a comment to this post:
		// https://plus.google.com/+LucasRocha/posts/37U8GWtYxDE
		recyclerView.addOnItemTouchListener(this);
		gestureDetector = new GestureDetectorCompat(this, new RecyclerViewDemoOnGestureListener());

	}

	private GestureDetectorCompat gestureDetector;

	private class RecyclerViewDemoOnGestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
			onClick(view);
			return super.onSingleTapConfirmed(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view) {
		int idx = recyclerView.getChildPosition(view);
		String currentItem = recyclerViewAdapter.getItem(idx);
		if (currentItem != null) {
			Toast.makeText(this, "Selected Item:" + currentItem, Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v7.widget.RecyclerView.OnItemTouchListener#
	 * onInterceptTouchEvent(android.support.v7.widget.RecyclerView,
	 * android.view.MotionEvent)
	 */
	@Override
	public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent e) {
		//https://plus.google.com/+LucasRocha/posts/37U8GWtYxDE
		gestureDetector.onTouchEvent(e);
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v7.widget.RecyclerView.OnItemTouchListener#onTouchEvent
	 * (android.support.v7.widget.RecyclerView, android.view.MotionEvent)
	 */
	@Override
	public void onTouchEvent(RecyclerView recyclerView, MotionEvent e) {
	}
}