package ar.edu.unlp.info.oo1.ejercicio10_jobScheduler;

public class JobSchedulerLIFO extends JobScheduler{
    
    @Override
	public JobDescription next() {
		if(!this.jobs.isEmpty()) {
			JobDescription nextJob = jobs.get(jobs.size()-1);
		    this.unschedule(nextJob);
        	return nextJob;
		}
		return null;
	}
}
