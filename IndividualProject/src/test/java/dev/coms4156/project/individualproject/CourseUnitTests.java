package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/** This class tests the Course class. */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourse() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
    testCourse.setEnrolledStudentCount(249);
  }

  @Test
  public void enrolledStudentCountTest() {
    assertFalse(testCourse.isCourseFull());
    testCourse.enrollStudent();
    assertTrue(testCourse.isCourseFull());
    testCourse.dropStudent();
    assertFalse(testCourse.isCourseFull());
  }
  
  @Test
  public void changeInstructorTest() {
    testCourse.reassignInstructor("Gail Kaiser");
    assertEquals("Gail Kaiser", testCourse.getInstructorName());
  }

  @Test
  public void changeLocationTest() {
    testCourse.reassignLocation("501 NWC");
    assertEquals("501 NWC", testCourse.getCourseLocation());
  }

  @Test
  public void changeTimeTest() {
    testCourse.reassignTime("10:10-11:25");
    assertEquals("10:10-11:25", testCourse.getCourseTimeSlot());
  }

  @AfterAll
  public static void toStringTest() {
    String expectedResult = "\nInstructor: Gail Kaiser; Location: 501 NWC; Time: 10:10-11:25";
    assertEquals(expectedResult, testCourse.toString());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}
