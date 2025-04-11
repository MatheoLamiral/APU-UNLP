package ar.edu.unlp.info.oo1.ejercicio10_jobScheduler;

public class JobSchedulerFIFO extends JobScheduler{
	
	@Override
	public JobDescription next() {
		if(!this.jobs.isEmpty()) {
			JobDescription nextJob = jobs.get(0);
        	this.unschedule(nextJob);
        	return nextJob;
		}
		return null;
	}

	
}
