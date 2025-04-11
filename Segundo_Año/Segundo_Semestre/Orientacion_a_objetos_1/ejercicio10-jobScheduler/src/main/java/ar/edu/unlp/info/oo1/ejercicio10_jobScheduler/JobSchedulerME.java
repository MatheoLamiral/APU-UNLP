package ar.edu.unlp.info.oo1.ejercicio10_jobScheduler;

public class JobSchedulerME extends JobScheduler{

	@Override
	public JobDescription next() {
		if(!this.jobs.isEmpty()) {
			JobDescription nextJob = this.jobs.stream()
					.max((j1,j2) -> Double.compare(j1.getEffort(), j2.getEffort()))
                    .orElse(null);
			this.unschedule(nextJob);
        	return nextJob;
		}
		return null;
	}
}
