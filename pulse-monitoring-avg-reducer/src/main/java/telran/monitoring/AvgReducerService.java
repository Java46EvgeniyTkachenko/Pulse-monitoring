package telran.monitoring;

import telran.monitoring.model.PulseProbe;

public interface AvgReducerService {
	Integer reduce(PulseProbe obj);

}
