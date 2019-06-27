package fr.sma.webconfboard;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.sma.webconfboard.entities.*;

import fr.sma.webconfboard.services.Conference.ConferenceServiceImpl;
import fr.sma.webconfboard.services.User.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
public class WebConfBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebConfBoardApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(UserServiceImpl userService, ConferenceServiceImpl conferenceService) {
		return args -> {
			// read json and write to db

			ObjectMapper mapper = new ObjectMapper();
			TypeReference <User[]> typeReferenceUser = new TypeReference<User[]>(){};
			TypeReference <Conference[]> typeReferenceConference = new TypeReference<Conference[]>(){};

			InputStream inputStreamUser = TypeReference.class.getResourceAsStream("/json/users.json");
			InputStream inputStreamConference = TypeReference.class.getResourceAsStream("/json/conferences.json");

			try {
				User[] usersList = mapper.readValue(inputStreamUser, typeReferenceUser);

				System.out.println(usersList);

				Stream.of(usersList).forEach(obj-> {
					System.out.println(obj);
					userService.save((obj));
					System.out.println("obj user saved");
				});

				System.out.println("Users Saved !");
				List<User> usersSaved = new ArrayList<User>();
				usersSaved = userService.getAllUsers();
				for(int i=0; i< usersSaved.size(); i++) {
					System.out.println(usersSaved.get(i));
				}
			} catch (IOException e) {
				System.out.println("Unable to save users : " + e.getMessage());
			}

			try {
				Conference[] conferencesList = mapper.readValue(inputStreamConference, typeReferenceConference);

				System.out.println(conferencesList);

				Stream.of(conferencesList).forEach(obj-> {
					System.out.println(obj);
					conferenceService.save((obj));
					System.out.println("obj conference saved");
				});

				System.out.println("Conferences Saved !");
				List<Conference> conferencesSaved = new ArrayList<Conference>();
				conferencesSaved = conferenceService.getAllConferences();
				for(int i=0; i< conferencesSaved.size(); i++) {
					System.out.println(conferencesSaved.get(i));
				}
			} catch (IOException e) {
				System.out.println("Unable to save conferences : " + e.getMessage());
			}

		};
	}
}
