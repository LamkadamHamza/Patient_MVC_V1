package ma.enset.Patient_MVC;

import ma.enset.Patient_MVC.entities.Patient;
import ma.enset.Patient_MVC.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import java.beans.Encoder;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class PatientMvcApplication {

	public static void main(String[] args) {


		SpringApplication.run(PatientMvcApplication.class, args);


	}

	@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return  args -> {
			patientRepository.save(new Patient(null,"HAmza",new Date(),false,12));
			patientRepository.save(new Patient(null,"nabil",new Date(),true,12));
			patientRepository.save(new Patient(null,"samira",new Date(),false,12));
			patientRepository.save(new Patient(null,"hana",new Date(),true,12));

			List<Patient>  listPatients =patientRepository.findAll();
			System.out.println("*************************");
		 listPatients.forEach(patient -> {
			System.out.println(patient.getNom());
		  });
		};
	}

	@Bean
    PasswordEncoder  passwordEncoder (){
		return new  BCryptPasswordEncoder();
	}

	/*
	@Bean
	CommandLineRunner commandLineRunnerjdbc (JdbcUserDetailsManager jdbcUserDetailsManager){
		PasswordEncoder passwordEncode= passwordEncoder();
		return  args -> {
			jdbcUserDetailsManager.createUser(
					User.withUsername("user1").password(passwordEncode.encode("123")).roles("USER").build()
			);
			jdbcUserDetailsManager.createUser(
					User.withUsername("user2").password(passwordEncode.encode("123")).roles("USER").build()
			);

			jdbcUserDetailsManager.createUser(
					User.withUsername("admin").password(passwordEncode.encode("123")).roles("USER","ADMIN").build()
			);
		};
	}
 */

	//@Bean
	CommandLineRunner commandLineRunnerjdbc (JdbcUserDetailsManager jdbcUserDetailsManager){
		PasswordEncoder passwordEncode= passwordEncoder();
		return  args -> {
			jdbcUserDetailsManager.createUser(
					User.withUsername("user3").password(passwordEncode.encode("123")).roles("USER","ADMIN").build()
			);
		};
	}
}
