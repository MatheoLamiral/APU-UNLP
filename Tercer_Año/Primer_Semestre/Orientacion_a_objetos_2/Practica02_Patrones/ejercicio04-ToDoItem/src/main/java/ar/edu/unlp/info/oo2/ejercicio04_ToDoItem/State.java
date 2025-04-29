package ar.edu.unlp.info.oo2.ejercicio04_ToDoItem;

import java.time.Duration;
import java.util.*;

public abstract class State {
	private List<String> comments;

	public abstract void start(ToDoItem context);
	public abstract void togglePause(ToDoItem context);
	public abstract void finish(ToDoItem context);
	public abstract Duration workedTime(ToDoItem context);
	public abstract void addComment(String comment);
	
	protected List<String> getComments(){
		return this.comments;
	}
}
 