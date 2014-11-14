/**
 * TTIKUSMEZER Copyright Turkcell Teknoloji 2014
 */
package com.turkcell.androidnew;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.com.turkcell.androidnew.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
	//Use Instead Of This The https://github.com/lucasr/twoway-view
	private List<String> elementList = new ArrayList<String>();

	public RecyclerViewAdapter() {
		elementList.add("test1");
		elementList.add("test2");
		elementList.add("test3");
		elementList.add("test4");
		elementList.add("test5");
		elementList.add("test6");
		elementList.add("test7");
		elementList.add("test8");
		elementList.add("test9");
	}

	public String getItem(int index)
	{
		int targetSize = elementList.size() - 1;
		if(index < targetSize)
		{
			return elementList.get(index);
		}
		return null;
	}
	
	 /**
     * Adds and item into the underlying data set
     * at the position passed into the method.
     *
     * @param newModelData The item to add to the data set.
     * @param position The index of the item to remove.
     */
    public void addData(String newModelData, int position) {
    	elementList.add(position, newModelData);
        notifyItemInserted(position);
    }

    /**
     * Removes the item that currently is at the passed in position from the
     * underlying data set.
     *
     * @param position The index of the item to remove.
     */
    public void removeData(int position) {
    	elementList.remove(position);
        notifyItemRemoved(position);
    }
	
	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ImageView image;
		public TextView text;

		public ViewHolder(View itemView) {
			super(itemView);
			image = (ImageView) itemView.findViewById(R.id.image);
			text = (TextView) itemView.findViewById(R.id.text);
		}
	}

	@Override
	public int getItemCount() {
		return elementList.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		viewHolder.text.setText(elementList.get(position));
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerlistitem, viewGroup,false);
		ViewHolder holder = new ViewHolder(itemView);
		return holder;
	}
}