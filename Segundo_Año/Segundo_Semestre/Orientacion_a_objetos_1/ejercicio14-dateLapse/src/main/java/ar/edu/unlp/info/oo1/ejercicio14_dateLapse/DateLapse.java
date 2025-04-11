package ar.edu.unlp.info.oo1.ejercicio14_dateLapse;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import ar.edu.unlp.info.oo1.ejercicio14_dateLapseInterface.DateLapseInterface;

public class DateLapse implements DateLapseInterface {
	private LocalDate from;
	private LocalDate to;
	
	public DateLapse(LocalDate from, LocalDate to) {
		this.from = from;
		this.to = to;
	}
	
	public DateLapse(int yearFrom, int monhtFrom, int dayFrom, int yearTo, int monhtTo, int dayTo) {
		this.from = LocalDate.of(yearFrom, monhtFrom, dayFrom);
		this.to = LocalDate.of(yearTo, monhtTo, dayTo);
	}
	
	public int sizeInDays() {
		return (int)this.from.until(this.to,ChronoUnit.DAYS);
	}
	
	public boolean includesDate(LocalDate date) {
		return (((date.isAfter(this.from))||(date.isEqual(this.from)))&&((date.isBefore(this.to))||(date.isEqual(this.to))));
	}
	
	public LocalDate getFrom() {
		return from;
	}
	public LocalDate getTo() {
		return to;
	}
	
	
}
