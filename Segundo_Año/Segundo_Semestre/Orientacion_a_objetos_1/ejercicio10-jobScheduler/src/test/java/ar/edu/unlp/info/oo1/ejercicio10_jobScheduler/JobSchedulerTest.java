package ar.edu.unlp.info.oo1.ejercicio10_jobScheduler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JobSchedulerTest {
    protected JobDescription firstJob;
    protected JobDescription highestPriorityJob;
    protected JobDescription mostEffortJob;
    protected JobDescription lastJob;
    protected JobSchedulerFIFO fifo;
    protected JobSchedulerLIFO lifo;
    protected JobSchedulerME me;
    protected JobSchedulerHP hp;

    private void initializeJobs() {
        firstJob = new JobDescription (1, 1, "Este es el primero");
        highestPriorityJob = new JobDescription (1, 100, "Este es el de más prioridad");
        mostEffortJob = new JobDescription (100, 1, "Este es el de más esfuerzo");
        lastJob = new JobDescription (1, 1, "Este es el último");
    }
    
    private void testNext(JobScheduler scheduler, JobDescription job, JobDescription job2) {
        	scheduler.schedule(this.firstJob);
        	scheduler.schedule(this.highestPriorityJob);
        	scheduler.schedule(this.mostEffortJob);
        	scheduler.schedule(this.lastJob);
            JobDescription nextJob = scheduler.next();
            assertEquals(nextJob, job);
            assertNotEquals(nextJob, job2);
            System.out.println(scheduler.getJobs().size());
            assertEquals(scheduler.getJobs().size(), 3);
    }

    @BeforeEach
    void setUp() {
    	this.fifo = new JobSchedulerFIFO();
    	this.lifo = new JobSchedulerLIFO();
    	this.me = new JobSchedulerME();
    	this.hp = new JobSchedulerHP();
        this.initializeJobs();
        
    }

    @Test
    void testNextFIFO() {
    	this.testNext(this.fifo, this.firstJob, this.highestPriorityJob);
    }
    
    @Test
    void testNextLIFO() {
    	this.testNext(this.lifo, this.lastJob, this.firstJob);
    }
    
    @Test
    void testNextME() {
    	this.testNext(this.me, this.mostEffortJob, this.highestPriorityJob);
    }
    
    @Test
    void testNextHP() {
    	this.testNext(this.hp, this.highestPriorityJob, this.mostEffortJob);
    }
}
