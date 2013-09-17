package com.berlogamobile.fitget;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import com.pras.*;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.berlogamobile.fitget.R;

public class FitgetActivity extends Activity {

	FitgetStats fStats;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
      
        
        fStats = this.loadFitgetStats();

        
        this.setStatsView(fStats, true);
    	
       
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
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("MMM d");
	    	t = (TextView)findViewById(R.id.textLast);
	    	t.setText(sdf.format(fStats.getLast()));
	    	
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
    
    
    private FitgetStats loadFitgetStats() {
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