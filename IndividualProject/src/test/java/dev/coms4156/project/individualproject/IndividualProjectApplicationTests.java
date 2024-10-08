package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/** This class tests the IndividualProjectApplication class. */
@SpringBootTest
@ContextConfiguration
public class IndividualProjectApplicationTests {

  /** setup IndividualProjectApplication and mocks for testing. */
  @BeforeEach
  public void setUpIndividualProjectApplicationAndMocksForTesting() {
    testApplication = new IndividualProjectApplication();
    mockDatabase = Mockito.mock(MyFileDatabase.class);
    mockSpringApplication = Mockito.mockStatic(SpringApplication.class);
  }

  @AfterEach
  public void cleanUpMocks() {
    mockSpringApplication.close();
  }

  @Test
  public void mainTest() {
    String[] args = {};
    IndividualProjectApplication.main(args);
    mockSpringApplication.verify(
        () -> SpringApplication.run(IndividualProjectApplication.class, args));
  }

  @Test
  public void runTest() {
    String[] args = {};
    testApplication.run(args);
    assertNotNull(IndividualProjectApplication.myFileDatabase);
  }

  @Test
  public void runSetupTest() {
    String[] args = {"setup"};
    testApplication.run(args);
    assertNotNull(IndividualProjectApplication.myFileDatabase);
  }

  @Test
  public void overrideDatabaseAndTerminationTest() {
    IndividualProjectApplication.overrideDatabase(mockDatabase);
    assertNotNull(IndividualProjectApplication.myFileDatabase);
    testApplication.onTermination();
    Mockito.verify(mockDatabase, Mockito.never()).saveContentsToFile();
  }

  /** The mock database and mock spring application used for testing. */
  public static MyFileDatabase mockDatabase;

  public static MockedStatic<SpringApplication> mockSpringApplication;

  /** The test application instance used for testing. */
  public static IndividualProjectApplication testApplication;
}
