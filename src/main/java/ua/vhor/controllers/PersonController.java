package ua.vhor.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.vhor.entity.Person;
import ua.vhor.entity.PersonInfo;
import ua.vhor.repository.PersonInfoRepository;
import ua.vhor.repository.PersonRepository;

@RestController
public class PersonController {

	final static Logger logger = Logger.getLogger(PersonController.class);

	@Autowired
	PersonRepository personRepository;
	@Autowired
	PersonInfoRepository personInfoRepository;

	@RequestMapping("/getPersonByLogin")
	public Person getPersonByLogin(@RequestParam(value = "login") String login) {
		logger.info("Executing method getPersonByLogin()");
		Person person = null;
		try {
			List<Person> persons = personRepository.findByLogin(login);
			person = persons.size() > 0 ? persons.get(0) : null;
		} catch (Exception e) {
			logger.error("Exception in method getPersonByLogin() \n"
					+ e.getMessage());
		}
		return person;
	}

	@RequestMapping("/getPersonByLoginAndPassword")
	Person getPersonByLoginAndPassword(
			@RequestParam(value = "login") String login,
			@RequestParam(value = "password") String password) {
		logger.info("Executing method getPersonByLoginAndPassword()");
		Person person = null;
		try {
			List<Person> persons = personRepository.findByLoginAndPassword(
					login, password);
			person = persons.size() > 0 ? persons.get(0) : null;
		} catch (Exception e) {
			logger.error("Exception in method getPersonByLoginAndPassword() \n"
					+ e.getMessage());
		}
		return person;
	}

	@RequestMapping(value = "/savePersonWithInfo/{login}/{password}", method = RequestMethod.POST)
	public void savePersonWithPersonInfo(@PathVariable("login") String login,
			@PathVariable("password") String password,
			@RequestBody PersonInfo personInfo,
			HttpServletRequest httpServletRequest) throws IOException {
		logger.info("Executing method savePersonWithPersonInfo()");
		try {
			Person person = new Person(login, password);
			personRepository.save(person);
			personInfo.setPerson(person);
			personInfoRepository.save(personInfo);
		} catch (Exception e) {
			logger.error("Exception in method savePersonWithPersonInfo() \n"
					+ e.getMessage());
			e.printStackTrace();
		}

	}

}
