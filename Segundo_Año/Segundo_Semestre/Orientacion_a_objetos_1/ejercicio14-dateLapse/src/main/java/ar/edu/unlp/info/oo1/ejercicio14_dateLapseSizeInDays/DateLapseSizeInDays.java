package ar.edu.unlp.info.oo1.ejercicio14_dateLapseSizeInDays;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import ar.edu.unlp.info.oo1.ejercicio14_dateLapseInterface.DateLapseInterface;

public class DateLapseSizeInDays implements DateLapseInterface {
	private LocalDate from;
	private int sizeInDays;
	
	public DateLapseSizeInDays(LocalDate from, int sizeInDays) {
		this.from = from;
		this.sizeInDays = sizeInDays;
	}
	
	public DateLapseSizeInDays(int yearFrom, int monhtFrom, int dayFrom, int sizeInDays) {
		this.from = LocalDate.of(yearFrom, monhtFrom, dayFrom);
		this.sizeInDays = sizeInDays;
	}
	
	public int sizeInDays() {
		return this.sizeInDays;
	}
	
	public boolean includesDate(LocalDate date) {
		LocalDate to = this.from.plusDays(this.sizeInDays); 
		return (((date.isAfter(this.from))||(date.isEqual(this.from)))&&((date.isBefore(to))||(date.isEqual(to))));
	}
	
	public LocalDate getFrom() {
		return this.from;
	}
	
	
}
