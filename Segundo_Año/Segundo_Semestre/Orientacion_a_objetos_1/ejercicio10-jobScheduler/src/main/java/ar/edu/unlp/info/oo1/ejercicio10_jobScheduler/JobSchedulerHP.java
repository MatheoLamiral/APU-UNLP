package ar.edu.unlp.info.oo1.ejercicio10_jobScheduler;

public class JobSchedulerHP extends JobScheduler{

	@Override
	public JobDescription next() {
		if(!this.jobs.isEmpty()) {
			JobDescription nextJob = this.jobs.stream()
					.max((j1,j2) -> Double.compare(j1.getPriority(), j2.getPriority()))
                    .orElse(null);
			this.unschedule(nextJob);
        	return nextJob;
		}
		return null;
	}
}
