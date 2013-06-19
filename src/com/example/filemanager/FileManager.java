package com.example.filemanager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FileManager extends ListActivity implements View.OnClickListener {

	String root = "/";
	String prev = root;
	List<String> item = null;
	ArrayList<String> path = null;
	
	Button Sname, Ssize;
	TextView myPath;
	File file = null;
	File[] files = null;
	

	private void getDir(String root2) {
		// TODO Auto-generated method stub
		item = new ArrayList<String>();  
		path = new ArrayList<String>();  
		file = new File(root2);
		files = file.listFiles();
		myPath.setText("Location: " + root2);
		
		
		
		if (!root2.equals(root)) {
			item.add("<--Back");	
			path.add(file.getParent());
		}

		for (int i = 0; i < files.length; i++) {
			File f = files[i];
			path.add(f.getPath());
			if (f.isDirectory()) {
				if(f.listFiles() != null)
				item.add(f.getName().toString()+ "/" + "\n" + Integer.toString(f.list().length)+ " files in this Directory");
				else
				item.add(f.getName().toString()+ "/" + "\nNo File in this Directory");
					
			} else
				item.add(f.getName() + "\nSize :" + f.length() + " bytes");
		}
		setListAdapter(new ArrayAdapter<String>(FileManager.this,
				android.R.layout.simple_list_item_1, item));
		
	
	
	
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Sname = (Button) findViewById(R.id.bName);
		Ssize = (Button) findViewById(R.id.bSize);
		myPath = (TextView) findViewById(R.id.tvPath);
		Sname.setOnClickListener(this);
		Ssize.setOnClickListener(this);
		getDir(root);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bName:
			for (int i = 0; i < files.length; i++) {

				for (int j = 0; j < files.length - 1 - i; j++) {

					if (files[j].getName().compareTo(files[j + 1].getName()) > 0) {

						File temp = files[j];
						files[j] = files[j + 1];
						files[j + 1] = temp;
					}
				}
			}
			
			
			
			item = null;
			path=null;
			item = new ArrayList<String>();
			path = new ArrayList<String>();
			if (file.getParent()!= null) {
				item.add("<--Back");	
				path.add(file.getParent());
			}

			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				path.add(f.getPath());
				if (f.isDirectory()) {
					if(f.listFiles() != null)
					item.add(f.getName().toString()+ "/" + "\n" + Integer.toString(f.list().length)+ " files in this Directory");
					else
					item.add(f.getName().toString()+ "/" + "\nNo File in this Directory");
						
				} else
					item.add(f.getName() + "\nSize :" + f.length() + " bytes");
			}
			setListAdapter(new ArrayAdapter<String>(FileManager.this,
					android.R.layout.simple_list_item_1, item));
		
			
			
			break;
		case R.id.bSize:
			for (int i = 0; i < files.length; i++) {

				for (int j = 0; j < files.length -1 -i; j++) {
					int x=0;int y=0;
					if((files[j]).listFiles() != null)
						x+=(files[j]).listFiles().length;
					if(files[j + 1]
							.listFiles() != null)
						y+= files[j + 1]
								.listFiles().length;
				

					if ( x < y || ((!files[j].isDirectory()) && files[j+1].isDirectory())) {

						File temp = files[j];
						files[j] = files[j + 1];
						files[j + 1] = temp;
					}

					else if(((!files[j].isDirectory()) && !files[j+1].isDirectory()) && (files[j].length() < files[j+1].length() ))
					{
						File temp = files[j];
						files[j] = files[j + 1];
						files[j + 1] = temp;
						
					}
				}
			}
			
			
			
			
			item = null;
			path=null;
			item = new ArrayList<String>();
			path = new ArrayList<String>();
			if (file.getParent()!= null) {
				item.add("<--Back");	
				path.add(file.getParent());
			}

			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				path.add(f.getPath());
				if (f.isDirectory()) {
					if(f.listFiles() != null)
					item.add(f.getName().toString()+ "/" + "\n" + Integer.toString(f.list().length)+ " files in this Directory");
					else
					item.add(f.getName().toString()+ "/" + "\nNo File in this Directory");
						
				} else
					item.add(f.getName() + "\nSize :" + f.length() + " bytes");
			}
			setListAdapter(new ArrayAdapter<String>(FileManager.this,
					android.R.layout.simple_list_item_1, item));		
			

			
			
			break;
		}

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		File file = new File(path.get(position));
		if (file.isDirectory()) {
			if (file.canRead())
			{
				item =null;
				prev= file.getParent();
				getDir(path.get(position));
			}
		}
	}

}
