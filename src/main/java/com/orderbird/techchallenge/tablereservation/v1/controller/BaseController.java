package com.orderbird.techchallenge.tablereservation.v1.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.orderbird.techchallenge.tablereservation.v1.controller.BaseController.API_V1;


/**
 * Base Controller for table reservation, defines the base path of the APIs and any common or reusable data/logic.
 */

@RestController
@RequestMapping(value = API_V1)
@Api(value = API_V1, tags = API_V1, description = "")
public class BaseController {

    static final String API_V1 = "api" + "/" + "v1";

    static final String APPLICATION_JSON = "application/json";

    static final String HTTP_MESSAGE_200 = "Success";
    static final String HTTP_MESSAGE_400 = "One of the arguments is not valid";
}