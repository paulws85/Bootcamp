package com.pawel.wojtanka.bootcamp.controllers;

import com.pawel.wojtanka.bootcamp.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequiredArgsConstructor
public class RestExampleController {

    @GetMapping("/rest/hi")
    public ResponseEntity<String> sayHi() {
        return ResponseEntity.ok().body("Hi!");
    }

    @GetMapping("/rest/hello")
    public ResponseEntity<Message> sayHello() {
        return ResponseEntity.ok().body(new Message("Hello"));
    }

    @GetMapping("/rest/helloworld")
    @ResponseBody
    public Message getHelloWorld() {
        return new Message("Hello World!!");
    }

    @RequestMapping(value = "/rest/message", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED) //tylko gdy pomyślnie zostanie wykonana metoda
    @ResponseBody
    public void createMessage(@RequestBody final Message message) {
        System.out.println(message.getText());
    }

}
