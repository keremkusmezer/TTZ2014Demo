/**
 * TTIKUSMEZER Copyright Turkcell Teknoloji 2014
 */
package com.turkcell.androidnew;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.graphics.pdf.PdfRenderer.Page;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.com.turkcell.androidnew.R;

public class PdfViewerActivity extends Activity {
	private ImageView imageView;
	private ImageButton fbAdd;
	private ImageButton fbRemove;

	public PdfViewerActivity() {
	}

	private PdfRenderer renderer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pdfvieweractivity);
		fbAdd = (ImageButton) findViewById(R.id.fab_add);
		fbRemove = (ImageButton) findViewById(R.id.fab_remove);
		fbAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				currentPage++;
				render();
			}
		});
		fbRemove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				currentPage--;
				render();
			}
		});
		imageView = (ImageView) findViewById(R.id.pdfimageview);
		try {
			openFd = getResources().getAssets().openFd("test.mp3"); 
			parcelFileDescriptor = openFd.getParcelFileDescriptor();
			renderer = new PdfRenderer(parcelFileDescriptor);
		} catch (IOException e) {
			Log.i("Error", e.getMessage());
		}
		imageView.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				imageView.getViewTreeObserver().removeOnPreDrawListener(this);
				render();
				return false;
			}
		});
	}

	private AssetFileDescriptor openFd;
	private ParcelFileDescriptor parcelFileDescriptor;


	private Page page;
	private int currentPage;

	private void render() {
		try {
			if (currentPage < 0) {
				currentPage = 0;
			} else if (currentPage > renderer.getPageCount()) {
				currentPage = renderer.getPageCount() - 1;
			}
			page = renderer.openPage(currentPage);
			Page mCurrentPage = page;
			Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(), mCurrentPage.getHeight(), Bitmap.Config.ARGB_8888);
			page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
			page.close();
			imageView.setImageBitmap(bitmap);
			imageView.invalidate();
		} catch (Exception e) {
			Log.i("Exception", e.getMessage());
		}
	}

}