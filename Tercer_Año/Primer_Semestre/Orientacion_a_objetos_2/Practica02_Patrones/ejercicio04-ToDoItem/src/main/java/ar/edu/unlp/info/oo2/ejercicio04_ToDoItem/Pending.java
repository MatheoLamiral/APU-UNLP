package ar.edu.unlp.info.oo2.ejercicio04_ToDoItem;

import java.time.Duration;
import java.time.LocalDateTime;

public class Pending extends State {

	@Override
	public void start(ToDoItem context) {
		// TODO Auto-generated method stub
		context.setState(new InProgress());
		context.setFechaInicio(LocalDateTime.now());
	}

	@Override
	public void togglePause(ToDoItem context) {
		// TODO Auto-generated method stub
		throw new RuntimeException("El objeto ToDoItem no se encuentra en pause o in-progress (pending)");
	}

	@Override
	public void finish(ToDoItem context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Duration workedTime(ToDoItem context) {
		// TODO Auto-generated method stub
		throw new RuntimeException("El objeto ToDoItem aún esta en estado pendiente");
	}

	@Override
	public void addComment(String comment) {
		// TODO Auto-generated method stub
		this.getComments().add(comment);
	}

	

}
