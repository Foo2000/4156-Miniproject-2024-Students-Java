package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeEach
  public void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }

  @Test
  public void enrolledStudentCountTest() {
    testCourse.setEnrolledStudentCount(249);
    assertTrue(testCourse.enrollStudent());
    assertTrue(testCourse.isCourseFull());
    assertFalse(testCourse.enrollStudent());
    assertTrue(testCourse.dropStudent());
    assertFalse(testCourse.isCourseFull());
    testCourse.setEnrolledStudentCount(0);
    assertFalse(testCourse.dropStudent());
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

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}
