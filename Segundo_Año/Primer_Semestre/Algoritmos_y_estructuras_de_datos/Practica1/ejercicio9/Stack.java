package Practica1.ejercicio9;

import java.util.*;

import Practica1.ejercicio8.Sequence;

public class Stack <T> extends Sequence{
	private List <T> data;
	
	public Stack() {
		 this.data = new ArrayList<T>();
	}
	
	public void push(T dato) {
		 data.add(data.size(), dato);
	}
	
	public T pop() {
		 return data.remove(data.size()-1);
	}

	public T top() {
		 return data.get(data.size()-1);
	} 

	@Override
	public int size() {
		return data.size();
	}
	
	@Override
	public boolean isEmpty() {
		return data.size()==0;
	}
	
	@Override
	public String toString() {
		String str = "[";
		for(T d: data)
			str = str + d +", ";
		str = str.substring(0,str.length()-2)+"]";
		return str;
	}

}
