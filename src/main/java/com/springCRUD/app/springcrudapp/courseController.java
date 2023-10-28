package com.springCRUD.app.springcrudapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/courses")

public class courseController {

    @GetMapping()
    public List<Course> getAllCourses(){
        return Arrays.asList(
                new Course(1, "aws"),
                new Course(2, "java"),
                new Course(3, "js")
        );
    }
    record Course(int id, String name) { }

}
