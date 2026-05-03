package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo.entity.ApplicationRequest;
import com.example.demo.entity.Courses;
import com.example.demo.entity.Operators;
import com.example.demo.repository.CoursesRepository;
import com.example.demo.repository.OperatorsRepository;
import com.example.demo.repository.RequestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RepositoriesTest {

    @Autowired
    private CoursesRepository coursesRepository;

    @Autowired
    private OperatorsRepository operatorsRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Test
    void testCoursesRepository() {
        Courses course = new Courses();
        course.setName("Java");
        course.setDescription("Backend course");
        course.setPrice(100.0);

        Courses saved = coursesRepository.save(course);

        assertNotNull(saved.getId());
        assertEquals("Java", saved.getName());
    }

    @Test
    void testOperatorsRepository() {
        Operators operator = new Operators();
        operator.setName("John");
        operator.setSurname("Doe");
        operator.setDepartment("Support");

        Operators saved = operatorsRepository.save(operator);

        assertNotNull(saved.getId());
        assertEquals("John", saved.getName());
    }

    @Test
    void testRequestRepositoryHandled() {
        Courses course = new Courses();
        course.setName("Test Course");
        course.setDescription("Desc");
        course.setPrice(50.0);
        course = coursesRepository.save(course);

        ApplicationRequest request1 = new ApplicationRequest();
        request1.setUserName("Alice");
        request1.setPhone("+77712949203");
        request1.setCourse(course);
        request1.setHandled(false);

        ApplicationRequest request2 = new ApplicationRequest();
        request2.setUserName("Bob");
        request2.setPhone("+77772829202");
        request2.setCourse(course);
        request2.setHandled(true);

        requestRepository.save(request1);
        requestRepository.save(request2);

        List<ApplicationRequest> unhandled = requestRepository.findByHandledFalse();
        List<ApplicationRequest> handled = requestRepository.findByHandledTrue();

        assertEquals(1, unhandled.size());
        assertEquals(1, handled.size());
    }
}
