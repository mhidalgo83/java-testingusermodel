package com.lambdaschool.usermodel.services;

import com.lambdaschool.usermodel.UserModelApplication;
import com.lambdaschool.usermodel.models.Role;
import com.lambdaschool.usermodel.models.User;
import com.lambdaschool.usermodel.models.UserRoles;
import com.lambdaschool.usermodel.models.Useremail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.parser.Entity;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserModelApplication.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
//        List<User> users = userService.findAll();
//        for (User u : users) {
//            System.out.println(u.getUsername() + " " + u.getUserid());
//        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findUserById() {
        assertEquals("admin", userService.findUserById(4).getUsername());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findUserByIdNotFound() {
        assertEquals("admin", userService.findUserById(0).getUsername());
    }

    @Test
    public void findByNameContaining() {
        assertEquals(2, userService.findByNameContaining("tt").size());
    }

    @Test
    public void findAll() {
        assertEquals(5, userService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete() {
        userService.delete(4);
        assertEquals("admin", userService.findUserById(4));
    }

    @Test
    public void findByName() {
        assertEquals("barnbarn", userService.findByName("barnbarn").getUsername());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByNameNotFound() {
        assertEquals("barnbarn", userService.findByName("testtest"));
    }

    @Test
    public void save() {
        String userName = "Test Me";

        User u2 = new User(userName,
                "1234567",
                "test@test.com");
        u2.getUseremails()
                .add(new Useremail(u2,
                        "cinnamon@mymail.local"));
        u2.getUseremails()
                .add(new Useremail(u2,
                        "hops@mymail.local"));
        u2.getUseremails()
                .add(new Useremail(u2,
                        "bunny@email.local"));
        userService.save(u2);
        User addUser = userService.save(u2);
        assertNotNull(addUser);
        assertEquals(userName, addUser.getUsername());
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteAll() {
        userService.deleteAll();
        assertEquals(0, userService.findAll().size());
    }
}