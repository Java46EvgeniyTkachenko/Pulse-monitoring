package telran.monitoring;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import telran.monitoring.model.PulseProbe;

@SpringBootApplication
public class AvgReducerAppl {

	@Autowired
	AvgReducerService avgReducerService;
	@Autowired
	StreamBridge streamBridge;
	@Value("${app.binding.name:average-out-0}")
	private String bindingName;
	
	public static void main(String[] args) {
		SpringApplication.run(AvgReducerAppl.class, args);
	}
		@Bean
		Consumer<PulseProbe> pulseProbeConsumer() {
			return this::avgReducerService;
		}

		void avgReducerService(PulseProbe pulseProbe) {
		
			if(pulseProbe != null) {
				streamBridge.send(bindingName, pulseProbe);
			}
		}

}
