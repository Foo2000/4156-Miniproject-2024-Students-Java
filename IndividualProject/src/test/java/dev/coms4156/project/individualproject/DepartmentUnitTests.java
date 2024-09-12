package dev.coms4156.project.individualproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {

  @BeforeEach
  public void setupDepartmentForTesting() {
    Course coms4156 = new Course("Gail Kaiser", "501 NWC", "10:10-11:25", 120);
    HashMap<String, Course> courses = new HashMap<>();
    courses.put("4156", coms4156);
    testDepartment = new Department("COMS", courses, "Luca Carloni", 2700);
  }

  @Test
  public void departmentChairTest() {
    assertEquals("Luca Carloni", testDepartment.getDepartmentChair());
  }

  @Test
  public void numberOfMajorsTest() {
    assertEquals(2700, testDepartment.getNumberOfMajors());
    testDepartment.addPersonToMajor();
    assertEquals(2701, testDepartment.getNumberOfMajors());
    testDepartment.dropPersonFromMajor();
    assertEquals(2700, testDepartment.getNumberOfMajors());
  }

  @Test
  public void addCourseTest() {
    Course coms3827 = new Course("Daniel Rubenstein", "207 Math", "10:10-11:25", 300);
    testDepartment.addCourse("3827", coms3827);
    assertEquals(coms3827, testDepartment.getCourseSelection().get("3827"));
  }

  @Test
  public void createCourseTest() {
    testDepartment.createCourse("3827", "Daniel Rubenstein", "207 Math", "10:10-11:25", 300);
    Course createdCourse = testDepartment.getCourseSelection().get("3827");
    assertEquals("Daniel Rubenstein", createdCourse.getInstructorName());
    assertEquals("207 Math", createdCourse.getCourseLocation());
    assertEquals("10:10-11:25", createdCourse.getCourseTimeSlot());
    assertFalse(createdCourse.isCourseFull());
  }

  @Test
  public void toStringTest() {
    String expectedResult = "COMS 4156: \nInstructor: Gail Kaiser; Location: 501 NWC; Time: 10:10-11:25\n";
    assertEquals(expectedResult, testDepartment.toString());
  }

  /** The test department instance used for testing. */
  public static Department testDepartment;
}
