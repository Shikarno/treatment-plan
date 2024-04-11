package com.patients.demo;

import com.patients.demo.entity.TreatmentAction;
import com.patients.demo.entity.TreatmentPlan;
import com.patients.demo.repository.TreatmentPlanRepository;
import com.patients.demo.scheduler.TaskGeneratingJob;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(TaskGeneratingJob taskGeneratingJob, TreatmentPlanRepository repository) {
		return (args) -> {
				repository.save(
						TreatmentPlan.builder()
								.treatmentAction(TreatmentAction.ACTION_A)
								.subjectPatient("patient1")
								.startDateTime(LocalDateTime.now())
								.endDateTime(LocalDateTime.now().plusDays(1))
								.recurrencePattern("every day at 12:00")
								.build()
				);

			repository.save(
					TreatmentPlan.builder()
							.treatmentAction(TreatmentAction.ACTION_A)
							.subjectPatient("patient2")
							.startDateTime(LocalDateTime.now())
							.endDateTime(LocalDateTime.now().plusDays(1))
							.recurrencePattern("every day at 12:00 and 16:00")
							.build()
			);
			taskGeneratingJob.generateTasks();
		};

	}


}
