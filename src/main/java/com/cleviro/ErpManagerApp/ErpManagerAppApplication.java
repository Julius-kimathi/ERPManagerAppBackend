package com.cleviro.ErpManagerApp;

import com.cleviro.ErpManagerApp.model.masters.BloodGroup;
import com.cleviro.ErpManagerApp.model.masters.Salutation;
import com.cleviro.ErpManagerApp.model.people.Role;
import com.cleviro.ErpManagerApp.model.people.User;
import com.cleviro.ErpManagerApp.model.people.UserRoles;
import com.cleviro.ErpManagerApp.repository.masters.BloodGroupRepository;
import com.cleviro.ErpManagerApp.repository.masters.SalutationRepository;
import com.cleviro.ErpManagerApp.repository.people.RoleRepository;
import com.cleviro.ErpManagerApp.repository.people.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ErpManagerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpManagerAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository, BloodGroupRepository bloodGroupRepository, SalutationRepository salutationRepository) {
		return args -> {

			//Create SUPER_ADMIN,ADMIN & USER Roles
			Role superadminRole = roleRepository.findByRole(UserRoles.SUPER_ADMIN);
			if (superadminRole == null) {
				superadminRole = new Role();
				superadminRole.setRole(UserRoles.SUPER_ADMIN);
				roleRepository.save(superadminRole);
			}

			Role adminRole = roleRepository.findByRole(UserRoles.ADMIN);
			if (adminRole == null) {
				adminRole = new Role();
				adminRole.setRole(UserRoles.ADMIN);
				roleRepository.save(adminRole);
			}

			Role userRole = roleRepository.findByRole(UserRoles.USER);
			if (userRole == null) {
				userRole = new Role();
				userRole.setRole(UserRoles.USER);
				roleRepository.save(userRole);
			}

			//Create an SuperAdmin user
			User superAdmin = userRepository.findByEmail("super-admin@gmail.com");
			if (superAdmin == null) {
				superAdmin = new User()
						.setEmail("super-admin@gmail.com")
						.setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
						.setFirstName("Julius")
						.setLastName("Kimathi")
						.setRoles(Arrays.asList(superadminRole));
				userRepository.save(superAdmin);
			}

			//Create an Admin user
			User admin = userRepository.findByEmail("admin@gmail.com");
			if (admin == null) {
				admin = new User()
						.setEmail("admin@gmail.com")
						.setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
						.setFirstName("John")
						.setLastName("Doe")
						.setRoles(Arrays.asList(adminRole));
				userRepository.save(admin);
			}

			//Create a normal user
			User passenger = userRepository.findByEmail("user@gmail.com");
			if (passenger == null) {
				passenger = new User()
						.setEmail("user@gmail.com")
						.setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
						.setFirstName("Mira")
						.setLastName("Jane")
						.setRoles(Arrays.asList(userRole));
				userRepository.save(passenger);
			}

			//Create blood groups
			List<BloodGroup> bloodGroups = (List<BloodGroup>) bloodGroupRepository.findAll();
			if (bloodGroups.size() < 1){
				bloodGroups = new ArrayList<>(Arrays.asList(
						new BloodGroup().setName("A+"),
						new BloodGroup().setName("A-"),
						new BloodGroup().setName("B+"),
						new BloodGroup().setName("B-"),
						new BloodGroup().setName("AB+"),
						new BloodGroup().setName("AB-"),
						new BloodGroup().setName("O+"),
						new BloodGroup().setName("ABO"),
						new BloodGroup().setName("O-")));
				bloodGroupRepository.saveAll(bloodGroups);
			}

			//create salutations
			List<Salutation> salutations = (List<Salutation>) salutationRepository.findAll();
			if (salutations.size() < 1){
				salutations = Arrays.asList(
						new Salutation().setName("Prof."),
						new Salutation().setName("Dr."),
						new Salutation().setName("Gen."),
						new Salutation().setName("Col."),
						new Salutation().setName("Lt."),
						new Salutation().setName("Mr."),
				       new Salutation().setName("Mrs."),
						new Salutation().setName("Miss."),
						new Salutation().setName("Rev."));
				salutationRepository.saveAll(salutations);
			}



		};
	}

}
