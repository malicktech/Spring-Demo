package net.webapp;

import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import net.webapp.repository.SocieteRepository;
import net.webapp.entities.Societe;

@EnableEurekaClient
@SpringBootApplication
@Import({AppConfig.class})
public class SocieteApplication {

	public static void main(String[] args) {
		// SpringApplication.run(BourseSocieteServiceApplication.class, args);

		// get context & injections de données exemples au démarrage
		ApplicationContext ctx = SpringApplication.run(SocieteApplication.class, args);
		SocieteRepository societeRepository = ctx.getBean(SocieteRepository.class);
		Stream.of("ACCOR", "AIR LIQUIDE", "AIRBUS GROUP", "ALCATEL-LUCENT", "ALSTOM", "ARCELORMITTAL", "AXA",
				"BNP PARIBAS ACT.A", "BOUYGUES", "CAP GEMINI", "CARREFOUR", "CREDIT AGRICOLE", "DANONE", "EDF",
				"ESSILOR INTL.", "GDF SUEZ", "GEMALTO", "KERING", "LOREAL", "LAFARGE", "LEGRAND", "LVMH", "MICHELIN",
				"ORANGE", "PERNOD RICARD", "PUBLICIS GROUPE SA", "RENAULT", "SAFRAN", "SAINT GOBAIN", "SANOFI",
				"SCHNEIDER ELECTRIC", "SOCIETE GENERALE", "SOLVAY", "TECHNIP", "TOTAL", "UNIBAIL-RODAMCO", "VALEO",
				"VEOLIA ENVIRON.", "VINCI", "VIVENDI"
		).forEach(s -> societeRepository.save(new Societe(s)));
	}
}
