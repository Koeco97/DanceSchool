package com.example.danceSchool.exceptionHandler;

import com.example.danceSchool.exception.ClientException;
import com.example.danceSchool.exception.DanceException;
import com.example.danceSchool.exception.GroupException;
import com.example.danceSchool.exception.LessonException;
import com.example.danceSchool.exception.PersonException;
import com.example.danceSchool.exception.TeacherException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerService {
    @ExceptionHandler(PersonException.class)
    public ResponseEntity<Object> handler(PersonException e, WebRequest request) {
        return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handler(ClientException e, WebRequest request) {
        return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DanceException.class)
    public ResponseEntity<Object> handler(DanceException e, WebRequest request) {
        return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroupException.class)
    public ResponseEntity<Object> handler(GroupException e, WebRequest request) {
        return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LessonException.class)
    public ResponseEntity<Object> handler(LessonException e, WebRequest request) {
        return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeacherException.class)
    public ResponseEntity<Object> handler(TeacherException e, WebRequest request) {
        return new ResponseEntity<Object>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}

