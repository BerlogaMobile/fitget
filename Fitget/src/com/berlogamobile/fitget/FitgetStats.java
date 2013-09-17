package com.berlogamobile.fitget;

public class FitgetStats {
	
	private Integer year;
	private Integer month;
	private Integer week;
	private java.util.Date last;
	
	private Double distanceYear;
	private Double distanceMonth;
	private Double distanceWeek;
	private Double distanceLast;
	
	private TimeCalculator timeYear;
	private TimeCalculator timeMonth;
	private TimeCalculator timeWeek;
	private TimeCalculator timeLast;
	
	private Integer countYear;
	private Integer countMonth;
	private Integer countWeek;
	private Integer countLast = 1;
	
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public String getMonthName() {
        String monthString;
        
        switch (this.month) {
            case 1:  monthString = "Jan";
                     break;
            case 2:  monthString = "Feb";
                     break;
            case 3:  monthString = "Mar";
                     break;
            case 4:  monthString = "Apr";
                     break;
            case 5:  monthString = "May";
                     break;
            case 6:  monthString = "Jun";
                     break;
            case 7:  monthString = "Jul";
                     break;
            case 8:  monthString = "Aug";
                     break;
            case 9:  monthString = "Sep";
                     break;
            case 10: monthString = "Oct";
                     break;
            case 11: monthString = "Nov";
                     break;
            case 12: monthString = "Dec";
                     break;
            default: monthString = "Mmm";
                     break;
        }

		return monthString;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public java.util.Date getLast() {
		return last;
	}
	public void setLast(java.util.Date last) {
		this.last = last;
	}
	public Double getDistanceYear() {
		return distanceYear;
	}
	public void setDistanceYear(Double distanceYear) {
		this.distanceYear = distanceYear;
	}
	public Double getDistanceMonth() {
		return distanceMonth;
	}
	public void setDistanceMonth(Double distanceMonth) {
		this.distanceMonth = distanceMonth;
	}
	public Double getDistanceWeek() {
		return distanceWeek;
	}
	public void setDistanceWeek(Double distanceWeek) {
		this.distanceWeek = distanceWeek;
	}
	public Double getDistanceLast() {
		return distanceLast;
	}
	public void setDistanceLast(Double distanceLast) {
		this.distanceLast = distanceLast;
	}
	public TimeCalculator getTimeYear() {
		return timeYear;
	}
	public void setTimeYear(TimeCalculator timeYear) {
		this.timeYear = timeYear;
	}
	public TimeCalculator getTimeMonth() {
		return timeMonth;
	}
	public void setTimeMonth(TimeCalculator timeMonth) {
		this.timeMonth = timeMonth;
	}
	public TimeCalculator getTimeWeek() {
		return timeWeek;
	}
	public void setTimeWeek(TimeCalculator timeWeek) {
		this.timeWeek = timeWeek;
	}
	public TimeCalculator getTimeLast() {
		return timeLast;
	}
	public void setTimeLast(TimeCalculator timeLast) {
		this.timeLast = timeLast;
	}
	public Integer getCountYear() {
		return countYear;
	}
	public void setCountYear(Integer countYear) {
		this.countYear = countYear;
	}
	public Integer getCountMonth() {
		return countMonth;
	}
	public void setCountMonth(Integer countMonth) {
		this.countMonth = countMonth;
	}
	public Integer getCountWeek() {
		return countWeek;
	}
	public void setCountWeek(Integer countWeek) {
		this.countWeek = countWeek;
	}
	public Integer getCountLast() {
		return countLast;
	}
	public void setCountLast(Integer countLast) {
		this.countLast = countLast;
	}
	
}
