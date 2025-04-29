package ar.edu.unlp.info.oo2.ejercicio04_ToDoItem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StateTest {
	private ToDoItem pending;
    private ToDoItem inProgress;
    private ToDoItem paused;
    private ToDoItem finished;

    @BeforeEach
    void setUp() throws Exception {
        pending = new ToDoItem("task 1");
        inProgress = new ToDoItem("task 2");
        paused = new ToDoItem("task 3");
        finished = new ToDoItem("task 4");
        
        inProgress.start();
        paused.start();
        paused.togglePause();
        finished.start();
        finished.finish();
    }
    
    @Test
    void testStart() {
    	pending.start();
    	inProgress.start();
    	paused.start();
    	finished.start();
    	assertTrue(pending.getState() instanceof InProgress);
    	//para el resto no debria modificar el estado
    	assertTrue(inProgress.getState() instanceof InProgress);
    	assertTrue(paused.getState() instanceof Paused);
    	assertTrue(finished.getState() instanceof Finished);
    }
    
    @Test
    void testTogglePause() {
    	inProgress.togglePause();
    	paused.togglePause();
    	assertTrue(inProgress.getState() instanceof Paused);
    	assertTrue(paused.getState() instanceof InProgress);
    	//pending y finished generan exception
    	assertThrows(RuntimeException.class, () -> pending.togglePause());
    	assertThrows(RuntimeException.class, () -> finished.togglePause());
    }
    
    @Test
    void testFinish() {
    	pending.finish();
    	inProgress.finish();
    	paused.finish();
    	finished.finish();
    	assertTrue(inProgress.getState() instanceof Finished);
    	assertTrue(paused.getState() instanceof Finished);
    	//para el resto no debria modificar el estado
    	assertTrue(pending.getState() instanceof Pending);
    	assertTrue(finished.getState() instanceof Finished);
    }
    



}
