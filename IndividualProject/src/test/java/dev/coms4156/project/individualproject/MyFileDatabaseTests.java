package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseTests {

  @BeforeAll
  public static void setUpDatabaseForTesting() {
    testDatabase = new MyFileDatabase(1, "./testData.txt");
  }

  @Test
  public void myFileDatabaseTest() {
    Department compSci = new Department("COMS", new HashMap<>(), "Luca Carloni", 2700);
    Map<String, Department> mapping = new HashMap<>();
    mapping.put("COMS", compSci);

    testDatabase.setMapping(mapping);
    assertEquals(mapping, testDatabase.getDepartmentMapping());

    testDatabase.saveContentsToFile();
    MyFileDatabase newDatabase = new MyFileDatabase(0, "./testData.txt");
    assertEquals(
        "Luca Carloni", newDatabase.getDepartmentMapping().get("COMS").getDepartmentChair());

    File file = new File("./testData.txt");
    if (file.exists()) {
      file.delete();
    }
  }

  @AfterAll
  public static void toStringTest() {
    String expectedString = "For the COMS department: \n";
    assertEquals(expectedString, testDatabase.toString());
  }

  /** The test database instance used for testing. */
  public static MyFileDatabase testDatabase;
}
