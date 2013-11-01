package com.berlogamobile.fitget;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.berlogamobile.fitget.R;
import com.pras.SpreadSheet;
import com.pras.SpreadSheetFactory;
import com.pras.WorkSheetCell;
import com.pras.WorkSheetRow;
import com.pras.WorkSheet;


public class FitgetActivity extends Activity {

	FitgetStats fStats = new FitgetStats();
	ArrayList<SpreadSheet> spreadSheets;
	ArrayList<WorkSheet> workSheets;
	SpreadSheet sp;
	WorkSheet wk;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
      
    	Toast.makeText(this, "Reading from Google Docs... ", Toast.LENGTH_LONG).show();
       
        this.loadFitgetStats();

        
        //this.setStatsView(fStats, true);
    	
       
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu optMenu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, optMenu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.optRefresh:     
            	Toast.makeText(this, "1. You pressed "+item.getTitle(), Toast.LENGTH_LONG).show();
                break;
                
            case R.id.optSettings:     
            	Intent settingsActivity = new Intent(this,  FitgetSettings.class);
            	startActivity(settingsActivity);                
            	break;
                
            case R.id.optShare: 
            	Toast.makeText(this, "3. You pressed "+item.getTitle(), Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }
    
    private void setStatsView (FitgetStats fStats, boolean isMetric){
    	    	
    	TextView t;

    	if (fStats != null) {
    		
	        t = (TextView)findViewById(R.id.textYear);
	    	t.setText(fStats.getYear().toString());
	    	
	    	t = (TextView)findViewById(R.id.textMonth);
	    	t.setText(fStats.getMonthName());
	    	
	    	t = (TextView)findViewById(R.id.textWeek);
	    	t.setText(fStats.getWeek().toString());
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("MMM d", Locale.US);
	    	t = (TextView)findViewById(R.id.textLast);
	    	t.setText(sdf.format(fStats.getLast()));
	    	
	    	sdf= new SimpleDateFormat("hh:mm:ss, MMM d, yyyy", Locale.US);
	    	t = (TextView)findViewById(R.id.textUpdatedTimestamp);
	    	t.setText(sdf.format(fStats.getUpdated()));
	    	
	    	double mult;
	    	
	    	if (isMetric) {
	    		mult = 1; // conversion to km
	    	} else {
	    		mult = .625; // conversion to miles
	    	}
	
	    	DecimalFormat df = new DecimalFormat("#.##");
	    	
	    	t = (TextView)findViewById(R.id.textDistYear);
	    	t.setText(df.format(fStats.getDistanceYear().doubleValue()*mult));
	   	
	    	t = (TextView)findViewById(R.id.textDistMonth);
	    	t.setText(df.format(fStats.getDistanceMonth().doubleValue()*mult));
	   	
	    	t = (TextView)findViewById(R.id.textDistWeek);
	    	t.setText(df.format(fStats.getDistanceWeek().doubleValue()*mult));
	   	
	    	t = (TextView)findViewById(R.id.textDistLast);
	    	t.setText(df.format(fStats.getDistanceLast().doubleValue()*mult));
	   	
	    	t = (TextView)findViewById(R.id.textTimeYear);
	    	t.setText(fStats.getTimeYear().toString());
	   	
	    	t = (TextView)findViewById(R.id.textTimeMonth);
	    	t.setText(fStats.getTimeMonth().toString());
	   	
	    	t = (TextView)findViewById(R.id.textTimeWeek);
	    	t.setText(fStats.getTimeWeek().toString());
	   	
	    	t = (TextView)findViewById(R.id.textTimeLast);
	    	t.setText(fStats.getTimeLast().toString());
	    	
	    	String strSpeed, strPace;
	    	double speed;
	    	if (fStats.getTimeYear().getTotalHours()>0){
	    		speed = fStats.getDistanceYear().doubleValue()*mult/fStats.getTimeYear().getTotalHours(); 
	    		strSpeed = df.format(speed);
	    		strPace = df.format(60/speed);
	    	} else {
	    		strSpeed = "--";
	    		strPace = "--";
	    	}
	    	t = (TextView)findViewById(R.id.textSpeedYear);
	    	t.setText(strSpeed);
	    	t = (TextView)findViewById(R.id.textPaceYear);
	    	t.setText(strPace);
	   	
	    	if (fStats.getTimeMonth().getTotalHours()>0){
	    		speed = fStats.getDistanceMonth().doubleValue()*mult/fStats.getTimeMonth().getTotalHours(); 
	    		strSpeed = df.format(speed);
	    		strPace = df.format(60/speed);
	    	} else {
	    		strSpeed = "--";
	    		strPace = "--";
	    	}
	    	t = (TextView)findViewById(R.id.textSpeedMonth);
	    	t.setText(strSpeed);
	    	t = (TextView)findViewById(R.id.textPaceMonth);
	    	t.setText(strPace);
	   	
	    	if (fStats.getTimeWeek().getTotalHours()>0){
	    		speed = fStats.getDistanceWeek().doubleValue()*mult/fStats.getTimeWeek().getTotalHours(); 
	    		strSpeed = df.format(speed);
	    		strPace = df.format(60/speed);
	    	} else {
	    		strSpeed = "--";
	    		strPace = "--";
	    	}
	    	t = (TextView)findViewById(R.id.textSpeedWeek);
	    	t.setText(strSpeed);
	    	t = (TextView)findViewById(R.id.textPaceWeek);
	    	t.setText(strPace);
	   	    	
	    	if (fStats.getTimeLast().getTotalHours()>0){
	    		speed = fStats.getDistanceLast().doubleValue()*mult/fStats.getTimeLast().getTotalHours(); 
	    		strSpeed = df.format(speed);
	    		strPace = df.format(60/speed);
	    	} else {
	    		strSpeed = "--";
	    		strPace = "--";
	    	}
	    	t = (TextView)findViewById(R.id.textSpeedLast);
	    	t.setText(strSpeed);
	    	t = (TextView)findViewById(R.id.textPaceLast);
	    	t.setText(strPace);
	   	
	    	t = (TextView)findViewById(R.id.textCountYear);
	    	t.setText(fStats.getCountYear().toString());
	   	
	    	t = (TextView)findViewById(R.id.textCountMonth);
	    	t.setText(fStats.getCountMonth().toString());
	   	
	    	t = (TextView)findViewById(R.id.textCountWeek);
	    	t.setText(fStats.getCountWeek().toString());
	   	
	    	t = (TextView)findViewById(R.id.textCountLast);
	    	t.setText(fStats.getCountLast().toString());
    	}
    	
    	int unit;
    	
    	if (isMetric) {
    		unit = R.string.m_unit_distance;
    	} else {
    		unit = R.string.e_unit_distance;
    	}
 
    	t = (TextView)findViewById(R.id.textViewDist1);
    	t.setText(unit);
    	t = (TextView)findViewById(R.id.textViewDist2);
    	t.setText(unit);
    	t = (TextView)findViewById(R.id.textViewDist3);
    	t.setText(unit);
    	t = (TextView)findViewById(R.id.textViewDist4);
    	t.setText(unit);
    	
    	if (isMetric) {
    		unit = R.string.m_unit_speed;
    	} else {
    		unit = R.string.e_unit_speed;
    	}
 
    	t = (TextView)findViewById(R.id.textViewSpeed1);
    	t.setText(unit);
    	t = (TextView)findViewById(R.id.textViewSpeed2);
    	t.setText(unit);
    	t = (TextView)findViewById(R.id.textViewSpeed3);
    	t.setText(unit);
    	t = (TextView)findViewById(R.id.textViewSpeed4);
    	t.setText(unit);
	    
    	if (isMetric) {
    		unit = R.string.m_unit_pace;
    	} else {
    		unit = R.string.e_unit_pace;
    	}
 
    	t = (TextView)findViewById(R.id.textViewPace1);
    	t.setText(unit);
    	t = (TextView)findViewById(R.id.textViewPace2);
    	t.setText(unit);
    	t = (TextView)findViewById(R.id.textViewPace3);
    	t.setText(unit);
    	t = (TextView)findViewById(R.id.textViewPace4);
    	t.setText(unit);
	    
    }
    
    private void loadFitgetStats() {
        // Init and Read SpreadSheet list from Google Server
        runOnUiThread(new Runnable(){
        	public void run(){
        		new DataLoadingTask().execute((Object)null);
        	}
        });

    }
    
	private class DataLoadingTask extends AsyncTask{

		//Dialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			/*
			dialog = new Dialog(FitgetActivity.this);
			dialog.setTitle("Please wait");
			TextView tv = new TextView(FitgetActivity.this.getApplicationContext());
			tv.setText("Reading SpreadSheet list from your account...");
			dialog.setContentView(tv);
			dialog.show();
			*/
		}

		@Override
		protected Object doInBackground(Object... params) {
			// Read Spread Sheet list from the server.
			
			// Android Phone Authenticate Line
			//SpreadSheetFactory factory = SpreadSheetFactory.getInstance(new AndroidAuthenticator(GSSAct.this));
			
			// AVD BEGIN
			SpreadSheetFactory factory = SpreadSheetFactory.getInstance("your.account@gmail.com", "Your-Password");
		    spreadSheets = factory.getAllSpreadSheets();
		    // AVD END
		    
		    
		    
			//Looper.myLooper().prepare();
			
			// Android Phone Factory Line
			//spreadSheets = factory.getAllSpreadSheets(true, "World", false);
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//if(dialog.isShowing()) dialog.cancel();
			
			if(spreadSheets == null || spreadSheets.size() == 0){
		    	Toast.makeText(FitgetActivity.this.getApplicationContext(), "No spreadsheets exist in your account...", Toast.LENGTH_LONG).show();
		    }
		    else{
		    	for(int i=0; i<spreadSheets.size(); i++){
		    		sp = spreadSheets.get(i);
		    		if ("My Tracks-running".equals(sp.getTitle())){
				    	//Toast.makeText(FitgetActivity.this.getApplicationContext(), "Found Activity SpreadSheet: "+ sp.getTitle(), Toast.LENGTH_LONG).show();
				    	//int spID = i;
						//SpreadSheetFactory factory = SpreadSheetFactory.getInstance();
						// Read from local Cache
						//ArrayList<SpreadSheet> sps = factory.getAllSpreadSheets(false);
						//SpreadSheet sp = sps.get(spID); 
						new WorksheetLoadingTask().execute((Object)null);
						break;
		    		}
		    	}
		    	
		    }
		}

	}

 
	private class WorksheetLoadingTask extends AsyncTask{

		//Dialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			/*
			dialog = new Dialog(FitgetActivity.this);
			dialog.setTitle("Please wait");
			TextView tv = new TextView(FitgetActivity.this.getApplicationContext());
			tv.setText("Reading WorkSheet list from the SpreadSheet...");
			dialog.setContentView(tv);
			dialog.show();
	    	Toast.makeText(FitgetActivity.this.getApplicationContext(), "Processing Spreadsheet:"+sp.getTitle(), Toast.LENGTH_LONG).show();
	    	*/
		}

		@Override
		protected Object doInBackground(Object... params) {
			// Read Spread Sheet list from the server.
			workSheets = sp.getAllWorkSheets();
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//if(dialog.isShowing()) dialog.cancel();
			
			if(workSheets == null || workSheets.size() == 0){
		    	Toast.makeText(FitgetActivity.this.getApplicationContext(), "No WorkSheets exist in your account...", Toast.LENGTH_LONG).show();
		    }
		    else{
		    	//Toast.makeText(FitgetActivity.this.getApplicationContext(), "WorkSheet Count:"+workSheets.size(), Toast.LENGTH_LONG).show();
		    	for(int j=0; j<workSheets.size(); j++){
		    		wk = workSheets.get(j);
			    	//Toast.makeText(FitgetActivity.this.getApplicationContext(), "WorkSheet:"+sp.getTitle()+"->"+wk.getTitle(), Toast.LENGTH_LONG).show();
		    		if ("FitgetSample".equals(wk.getTitle())){
				    	//Toast.makeText(FitgetActivity.this.getApplicationContext(), "Found Data WorkSheet: "+ wk.getTitle(), Toast.LENGTH_LONG).show();
						new MetricsLoadingTask().execute((Object)null);
						break;
		    		}
		    	}
		    }
		}
	}

	private class MetricsLoadingTask extends AsyncTask{

		ArrayList<WorkSheetRow> rows;
		String[] cols;
		//Dialog dialog;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			/*
			dialog = new Dialog(FitgetActivity.this);
			dialog.setTitle("Please wait");
			TextView tv = new TextView(FitgetActivity.this.getApplicationContext());
			tv.setText("Reading Workout Stats...");
			dialog.setContentView(tv);
			dialog.show();
			*/
		}

		@Override
		protected Object doInBackground(Object... params) {
			cols = wk.getColumns();
			rows = wk.getData(false);
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//if(dialog.isShowing()) dialog.cancel();
			
			if(rows == null || rows.size() == 0){
		    	Toast.makeText(FitgetActivity.this.getApplicationContext(), "No data was found in sheet "+wk.getTitle(), Toast.LENGTH_LONG).show();
				return;
			}
			
			StringBuffer record = new StringBuffer();
			
			
			if(cols != null){
				record.append("Columns: ["+ cols +"]\n");
			}
			record.append("Number of Records: "+ rows.size()+"\n");
			

	    	for(int i=0; i<rows.size(); i++){
				WorkSheetRow row = rows.get(i);
				record.append("[ Row ID "+ (i + 1) +" ]: ");
				
				ArrayList<WorkSheetCell> cells = row.getCells();
				record.append(" Num of cells = "+ cells.size() +" ]\n");
				for(int j=0; j<cells.size(); j++){
					WorkSheetCell cell = cells.get(j);
					record.append(cell.getName() +" = "+ cell.getValue() +"\n"); 
					switch (i) {
					case 0:
						if ("year".equals(cell.getName())){
							fStats.setYear(Integer.valueOf(cell.getValue()));
						} else if("month".equals(cell.getName())){
							fStats.setMonth(Integer.valueOf(cell.getValue()));
						} else if("week".equals(cell.getName())){
							fStats.setWeek(Integer.valueOf(cell.getValue()));
						} else if("last".equals(cell.getName())){
							try {
								fStats.setLast(new SimpleDateFormat("MM/dd/yyyy", Locale.US).parse(cell.getValue()));	
							} catch (Exception ex) {
								System.out.println("Error converting Date: "+cell.getValue());
								System.out.println(ex.getMessage());
							}
						}
						break;
					case 1:
						if ("year".equals(cell.getName())){
							fStats.setDistanceYear(Double.valueOf(cell.getValue()));
						} else if("month".equals(cell.getName())){
							fStats.setDistanceMonth(Double.valueOf(cell.getValue()));
						} else if("week".equals(cell.getName())){
							fStats.setDistanceWeek(Double.valueOf(cell.getValue()));
						} else if("last".equals(cell.getName())){
							fStats.setDistanceLast(Double.valueOf(cell.getValue()));							
						}
						break;
					case 2:
						if ("year".equals(cell.getName())){
							fStats.setTimeYear(new TimeCalculator(cell.getValue()));
						} else if("month".equals(cell.getName())){
							fStats.setTimeMonth(new TimeCalculator(cell.getValue()));
						} else if("week".equals(cell.getName())){
							fStats.setTimeWeek(new TimeCalculator(cell.getValue()));
						} else if("last".equals(cell.getName())){
							fStats.setTimeLast(new TimeCalculator(cell.getValue()));
						}
						break;
					case 3:
						if ("year".equals(cell.getName())){
							fStats.setCountYear(Integer.valueOf(cell.getValue()));
						} else if("month".equals(cell.getName())){
							fStats.setCountMonth(Integer.valueOf(cell.getValue()));
						} else if("week".equals(cell.getName())){
							fStats.setCountWeek(Integer.valueOf(cell.getValue()));
						} else if("last".equals(cell.getName())){
							fStats.setCountLast(Integer.valueOf(cell.getValue()));							
						}
						break;
					}
				}
			}
	    	fStats.setUpdated(new java.util.Date());
			FitgetActivity.this.setStatsView(fStats,true);
			System.out.println(record.toString());
	    	Toast.makeText(FitgetActivity.this.getApplicationContext(), "Your stats have been updated", Toast.LENGTH_LONG).show();
		}
	}
   
    private FitgetStats loadSampleFitgetStats() {
    	FitgetStats fStats = new FitgetStats();
    	
    	fStats.setYear(2013);
    	fStats.setMonth(6);
    	fStats.setWeek(25);
    	fStats.setLast(new java.util.Date());
    	
    	fStats.setDistanceYear(572.72);
    	fStats.setDistanceMonth(38.20);
    	fStats.setDistanceWeek(8.31);
    	fStats.setDistanceLast(8.31);
    	
    	fStats.setTimeYear(new TimeCalculator(50,56,33));
    	fStats.setTimeMonth(new TimeCalculator(3,17,65));
    	fStats.setTimeWeek(new TimeCalculator(0,44,27));
    	fStats.setTimeLast(new TimeCalculator(0,44,27));
    	
    	fStats.setCountYear(63);
    	fStats.setCountMonth(4);
    	fStats.setCountWeek(1);
    	fStats.setCountLast(1);
    	
    	fStats.setUpdated(new java.util.Date());

    	return fStats;
    }
    
    public void clickEnglish (View statsView) {
        this.setStatsView(fStats, false);    
        final Button btnEnglish = (Button) findViewById(R.id.btnEnglish);
        btnEnglish.setTextAppearance(getApplicationContext(), R.style.btn_selected);
        btnEnglish.setEnabled(false);
        final Button btnMetric = (Button) findViewById(R.id.btnMetric);
        btnMetric.setTextAppearance(getApplicationContext(), R.style.btn_unselected);
        btnMetric.setEnabled(true);

    }
    
    public void clickMetric (View statsView) {
        this.setStatsView(fStats, true);      
        final Button btnEnglish = (Button) findViewById(R.id.btnEnglish);
        btnEnglish.setTextAppearance(getApplicationContext(), R.style.btn_unselected);
        btnEnglish.setEnabled(true);
        final Button btnMetric = (Button) findViewById(R.id.btnMetric);
        btnMetric.setTextAppearance(getApplicationContext(), R.style.btn_selected);
        btnMetric.setEnabled(false);
    }
}