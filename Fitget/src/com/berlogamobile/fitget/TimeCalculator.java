package com.berlogamobile.fitget;

import com.berlogamobile.fitget.R;

public class TimeCalculator {
	
	private int hours;
	private int minutes;
	private int seconds;

	public TimeCalculator() {
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
	}

	public TimeCalculator(int h, int m, int s) {
		if(s>59) {
			int mm = s/60;
			s -= mm*60;
			m += mm;
		}
		if(m>59) {
			int hh = m/60;
			m -= hh*60;
			h += hh;
		}
		this.hours = h;
		this.minutes = m;
		this.seconds = s;
	}
	
	public TimeCalculator(double totalHours){
		this.hours = (int) Math.floor(totalHours);
		double totalMinutes = (totalHours-this.hours)*60;
		this.minutes = (int) Math.floor(totalMinutes);
		this.seconds = (int) Math.floor((totalMinutes-this.minutes)*60);
	}

	public TimeCalculator(String value) {
		// value is a String in format "HH:MM:SS"
		String delims = "[:]";
		String [] s = value.split(delims);
		this.hours= Integer.valueOf(s[0]).intValue();
		this.minutes= Integer.valueOf(s[1]).intValue();
		this.seconds= Integer.valueOf(s[2]).intValue();
	}

	public int getHours(){
		return this.hours;
	}

	public int getMinutes(){
		return this.minutes;
	}

	public int getSeconds(){
		return this.seconds;
	}
	
	public String toString(){
		String sec_pfx = "";
		String min_pfx = "";
		if (this.seconds<10) {
			sec_pfx = "0";
		}
		if (this.minutes<10) {
			min_pfx = "0";
		}
		return (Integer.valueOf(this.hours)).toString()+":"
				+min_pfx+(Integer.valueOf(this.minutes)).toString()+":"
				+sec_pfx+(Integer.valueOf(this.seconds)).toString();
	}
	
	public void setTime(int h, int m, int s){
		if(s>59) {
			int mm = s/60;
			s -= mm*60;
			m += mm;
		}
		if(m>59) {
			int hh = m/60;
			m -= hh*60;
			h += hh;
		}
		this.hours = h;
		this.minutes = m;
		this.seconds = s;		
	}
	
	public void addTimeValues (int h, int m, int s){
		s += this.seconds;
		if(s>59) {
			int mm = s/60;
			s -= mm*60;
			m += mm;
		}
		m += this.minutes;
		if(m>59) {
			int hh = m/60;
			m -= hh*60;
			h += hh;
		}
		h += this.hours;

		this.hours = h;
		this.minutes = m;
		this.seconds = s;		
	}
	
	public void addTimeFromCalculator (TimeCalculator t){
		this.addTimeValues(t.getHours(), t.getMinutes(), t.getSeconds());
	}
	
	public double getTotalHours(){
		return this.hours+(this.minutes+this.seconds/60.0)/60.0;
	}
}
