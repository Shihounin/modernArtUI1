package com.example.modernartui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSeekBarChangeListener {
	
	TextView view1, view2, view3, view4, view5;
	SeekBar seekBar;
	
	private int progress1 = 0;
	private DialogFragment dialog;
	
	private static final int MOREINFORMATION = 0;
	
	static private final String URL = "http://www.moma.org/";
	static private final String CHOOSER_TEXT = "Load " + URL + " with:";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setup the layout
		view1=(TextView)findViewById(R.id.textView1);
		view2=(TextView)findViewById(R.id.textView2);
		view3=(TextView)findViewById(R.id.textView3);
		view4=(TextView)findViewById(R.id.textView4);
		view5=(TextView)findViewById(R.id.textView5);
		seekBar=(SeekBar)findViewById(R.id.seekBar1);
		
		seekBar.setOnSeekBarChangeListener(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			showDialogFragment(MOREINFORMATION);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// Show desired Dialog
		void showDialogFragment(int dialogID) {

			switch (dialogID) {

			// Show AlertDialog
			case MOREINFORMATION:
				
				
				// Create a new AlertDialogFragment
				dialog = AlertDialogFragment.newInstance();

				// Show AlertDialogFragment
				dialog.show(getFragmentManager(), "Alert");

				break;

			}
		}
	

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			
		progress1 = arg1;
		//change the textviews color as the seekbar progress changes
		int color1 = Color.rgb(106+progress1,90-(progress1/100),205-(progress1*2));
		view1.setBackgroundColor(color1);
		int color2 = Color.rgb(255-(progress1*2),105-progress1,180-progress1);
		view2.setBackgroundColor(color2);
		int color3 = Color.rgb(128-progress1,0+(progress1/100),0+progress1);
		view3.setBackgroundColor(color3);
		int color4 = Color.rgb(65+(progress1/100),105+(progress1/100),225-(progress1*2));
		view5.setBackgroundColor(color4);
		
		
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	// Class that creates the AlertDialog
	@SuppressLint("InflateParams")
	public static class AlertDialogFragment extends DialogFragment {

			public static AlertDialogFragment newInstance() {
				return new AlertDialogFragment();
			}

			// Build AlertDialog using AlertDialog.Builder
			@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				
				//return new AlertDialog.Builder(getActivity())
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			    // Get the layout inflater
			    LayoutInflater inflater = getActivity().getLayoutInflater();

			    // Inflate and set the layout for the dialog
			    // Pass null as the parent view because its going in the dialog layout
			    return builder.setView(inflater.inflate(R.layout.customdialog, null))
			    		
						// User cannot dismiss dialog by hitting back button
						.setCancelable(false)
						
						// Set up Negative Button, which is actually the yes button, wanted the Visit MOMA on the left side
						.setNegativeButton("Visit MOMA",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										((MainActivity) getActivity())
												.linkToSite(true);
									}
								})
								
						// Set up Positive Button
						.setPositiveButton("Not Now",
								new DialogInterface.OnClickListener() {
									public void onClick(
											final DialogInterface dialog, int id) {
										((MainActivity) getActivity())
												.linkToSite(false);
									}
								}).create();
			}
		}

		protected void linkToSite(boolean shouldcontinue) {
			// TODO Auto-generated method stub
			if(shouldcontinue){
				// Create a base intent for viewing a URL 
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
		        intent.setData(Uri.parse(URL));
				Intent chooserIntent = Intent.createChooser(intent, CHOOSER_TEXT);

				// Start the chooser Activity, using the chooser intent
				startActivity(chooserIntent);
			}
		}
	
}
