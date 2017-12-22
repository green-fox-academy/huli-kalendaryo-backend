package com.greenfoxacademy.opal.kalendaryo.kalendaryo.controllers;

import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.AuthModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.EventResponse;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.model.UserModel;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.AuthModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.repository.UserModelRepository;
import com.greenfoxacademy.opal.kalendaryo.kalendaryo.service.EventResponseService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static com.greenfoxacademy.opal.kalendaryo.kalendaryo.authorization.AuthorizeKal.authorize;

@RestController
public class NotificationController {

  @Autowired
  EventResponseService eventResponseService;

  @Autowired
  AuthModelRepository authModelRepository;

  @Autowired
  UserModelRepository userModelRepository;

  @PostMapping(value = "/notification")
  public HttpStatus eventNotification(HttpServletRequest request) {
  EventResponse eventResponse = new EventResponse(request);

    if (!eventResponse.validate()) {
          eventResponseService.saveEventResponse(eventResponse);
          return HttpStatus.OK;
      } else {
          System.out.println("Missing: " + eventResponse.getMissingFields());
          return HttpStatus.NOT_ACCEPTABLE;
      }
  }
  
  @GetMapping("/accesstoken")
  public String getAccessToken(@RequestParam String authCode) throws IOException {
    return authorize(authCode);
  }

  @PostMapping("/auth")
  public void getRegistration(@RequestBody AuthModel authModel) throws IOException {
    if (!authModel.equals(authModelRepository.findByEmail(authModel.getEmail()))) {
      authModelRepository
          .save(new AuthModel(authModel.getEmail(), authModel.getAuthCode(), new UserModel()));
    }else {
      authModelRepository.save(authModel);
    }

    System.out.println(authModel.getAuthCode());
    String accessToken = authorize("4/AAAF5cjr4Q34gky7DWNiPN-berJt8491_L2ZWhVFn4_OBzIbncv4hu_5EJ4pma8tWj2EfwimsYJhtCFhpDo8h_A");
    System.out.println(accessToken);
  }
  
    @GetMapping(value = "/notification")
    public Iterable<EventResponse> showAllEventResponse() {
        Iterable<EventResponse> responses = eventResponseService.findAllEventResponse();
        System.out.println(responses);
        return responses;
    }
  }
