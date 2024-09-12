package dev.coms4156.project.individualproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

import org.mockito.Mockito;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration
public class RouteControllerTests {

    @BeforeEach
    public void setUpRouteControllerAndMocksForTesting() {
        testRouteController = new RouteController();
        mockDatabase = Mockito.mock(MyFileDatabase.class);
        mockSpringApplication = Mockito.mockStatic(SpringApplication.class);
        IndividualProjectApplication.overrideDatabase(mockDatabase);

        Course coms4156 = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
        coms4156.setEnrolledStudentCount(10);
        Map<String, Course> courses = new HashMap<>();
        courses.put("4156", coms4156);
        Department compSci = new Department("COMS", courses, "Luca Carloni", 2700);
        Map<String, Department> mapping = new HashMap<>();
        mapping.put("COMS", compSci);
        Mockito.when(mockDatabase.getDepartmentMapping()).thenReturn(mapping);
    }

    @AfterEach
    public void cleanUpMocks() {
        mockSpringApplication.close();
    }

    @Test
    public void retrieveDepartmentTest() {
        ResponseEntity<?> response = testRouteController.retrieveDepartment("COMS");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void retrieveCourseTest() {
        ResponseEntity<?> response = testRouteController.retrieveCourse("COMS", 4156);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void isCourseFullTest() {
        ResponseEntity<?> response = testRouteController.isCourseFull("COMS", 4156);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("false", response.getBody().toString());
    }

    @Test
    public void getMajorCtFromDeptTest() {
        ResponseEntity<?> response = testRouteController.getMajorCtFromDept("COMS");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void identifyDeptChairTest() {
        ResponseEntity<?> response = testRouteController.identifyDeptChair("COMS");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void findCourseLocationTest() {
        ResponseEntity<?> response = testRouteController.findCourseLocation("COMS", 4156);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void findCourseInstructorTest() {
        ResponseEntity<?> response = testRouteController.findCourseInstructor("COMS", 4156);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void findCourseTimeTest() {
        ResponseEntity<?> response = testRouteController.findCourseTime("COMS", 4156);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void addMajorToDeptTest() {
        ResponseEntity<?> response = testRouteController.addMajorToDept("COMS");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void removeMajorFromDeptTest() {
        ResponseEntity<?> response = testRouteController.removeMajorFromDept("COMS");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void dropStudentTest() {
        ResponseEntity<?> response = testRouteController.dropStudent("COMS", 4156);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void setEnrollmentCountTest() {
        ResponseEntity<?> response = testRouteController.setEnrollmentCount("COMS", 4156, 50);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void changeCourseTimeTest() {
        ResponseEntity<?> response = testRouteController.changeCourseTime("COMS", 4156, "10:10-11:25");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void changeCourseTeacherTest() {
        ResponseEntity<?> response = testRouteController.changeCourseTeacher("COMS", 4156, "Gail Kaiser");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void changeCourseLocationTest() {
        ResponseEntity<?> response = testRouteController.changeCourseLocation("COMS", 4156, "501 NWC");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    /** The mock database and mock spring application used for testing. */
    public static MyFileDatabase mockDatabase;
    public static MockedStatic<SpringApplication> mockSpringApplication;

    /** The test route controller instance used for testing. */
    public static RouteController testRouteController;
}
