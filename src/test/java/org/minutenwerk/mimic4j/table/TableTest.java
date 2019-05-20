package org.minutenwerk.mimic4j.table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.minutenwerk.mimic4j.table.Person.Gender;

public class TableTest {

	  private static final String BIRTHDAY_1 = "2002-02-02";
	  private static final LocalDate BIRTHDAY_DATE1 = LocalDate.parse(BIRTHDAY_1);

	  private static final String BIRTHDAY_2 = "2003-03-03";
	  private static final LocalDate BIRTHDAY_DATE2 = LocalDate.parse(BIRTHDAY_2);

  @Test
  public void test1() {
    // setup
	    Person testPerson1 = new Person().setVorname("John").setNachname("Doe").setGender(Gender.MALE)
	            .setBirthday(BIRTHDAY_DATE1);
	    Person testPerson2 = new Person().setVorname("Jane").setNachname("Doe").setGender(Gender.FEMALE)
	            .setBirthday(BIRTHDAY_DATE2);
	    List<Person> personen = new ArrayList<>();
	    personen.add(testPerson1);
	    personen.add(testPerson2);
	    Team team = new Team();
	    team.setName("Some team");
	    team.setPersonen(personen);
    PersonController controller = new PersonController();

    // test
    controller.team().set(team);
  }
}
