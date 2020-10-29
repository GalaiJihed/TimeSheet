package tn.esprit.spring.services;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.EmployeRepository;

/**
 * Test the User Service Implementation: test the service logic
 *
 * @author Charz++
 */

@RunWith(MockitoJUnitRunner.class)
public class EmployeServiceImplTest {

  private static final Long USER_ONE_ID = 1L;


  @Mock
  private EmployeRepository repoMock;

  @InjectMocks
  private EmployeServiceImpl userService;

  private Employe userDoc;
  
  @Before
  public void init() {
    userDoc = new Employe();
    userDoc.setId(1);
    userDoc.setNom("Carlos");
    userDoc.setPrenom("Charz");
    userDoc.setRole(Role.ADMINISTRATEUR);
    userDoc.setEmail("carlos@yopmail.com");

  
  }
  @Test
  public void getEmployePrenomById() {
    // Data preparation
    Mockito.when(repoMock.findById(1)).thenReturn(Optional.of(userDoc));

    // Method call
    String employe = userService.getEmployePrenomById(1);

    // Verification
    Assert.assertNotNull(employe);
    Mockito.verify(repoMock, Mockito.times(1)).findById(1);
    Mockito.verifyNoMoreInteractions(repoMock);
  }
  

}