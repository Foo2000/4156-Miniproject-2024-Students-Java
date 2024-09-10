package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/** This class tests the Course class. */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void enrolledStudentCountTest() {
    testCourse.setEnrolledStudentCount(249);
    assertFalse(testCourse.isCourseFull());
    testCourse.enrollStudent();
    assertTrue(testCourse.isCourseFull());
    testCourse.dropStudent();
    assertFalse(testCourse.isCourseFull());
  }
  
  @Test
  public void changeInstructorTest() {
    testCourse.reassignInstructor("Griffin Newbold - modified");
    assertEquals("Griffin Newbold - modified", testCourse.getInstructorName());
    testCourse.reassignInstructor("Griffin Newbold");
    assertEquals("Griffin Newbold", testCourse.getInstructorName());
  }

  @Test
  public void changeLocationTest() {
    testCourse.reassignLocation("417 IAB - modified");
    assertEquals("417 IAB - modified", testCourse.getCourseLocation());
    testCourse.reassignLocation("417 IAB");
    assertEquals("417 IAB", testCourse.getCourseLocation());
  }

  @Test
  public void changeTimeTest() {
    testCourse.reassignTime("11:40-12:55 - modified");
    assertEquals("11:40-12:55 - modified", testCourse.getCourseTimeSlot());
    testCourse.reassignTime("11:40-12:55");
    assertEquals("11:40-12:55", testCourse.getCourseTimeSlot());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}
