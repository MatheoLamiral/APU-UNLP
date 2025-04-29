package ar.edu.unlp.info.oo2.ejercicio04_ToDoItem;

import java.time.Duration;
import java.time.LocalDateTime;

public class InProgress extends State {

	@Override
	public void start(ToDoItem context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void togglePause(ToDoItem context) {
		// TODO Auto-generated method stub
		context.setState(new Paused());
	}

	@Override
	public void finish(ToDoItem context) {
		// TODO Auto-generated method stub
		context.setState(new Finished());
		context.setFechaFin(LocalDateTime.now());
	}

	@Override
	public Duration workedTime(ToDoItem context) {
		// TODO Auto-generated method stub
		return Duration.between(context.getFechaInicio(), LocalDateTime.now());
	}

	@Override
	public void addComment(String comment) {
		// TODO Auto-generated method stub
		this.getComments().add(comment);
	}

	
}
