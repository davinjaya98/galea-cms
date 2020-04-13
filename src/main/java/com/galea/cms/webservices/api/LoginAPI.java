package com.galea.cms.webservices.api;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//Services
import com.galea.cms.repository.service.AuthenticationService;

//Beans
import com.galea.cms.webservices.bean.GeneralWsResponseBean;

import com.galea.cms.webservices.bean.AuthenticationBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Login API", description="API regarding login logic")
public class LoginAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ResourceBundle labels = ResourceBundle.getBundle("messages");

    //services
    @Autowired
    AuthenticationService authenticationService;
    //services

    @ApiOperation(value = "Authenticate username and password", response = GeneralWsResponseBean.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Get the details"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/authenticate", consumes = { "application/json" }, produces = {
			"application/json" })
	public GeneralWsResponseBean authenticate(HttpServletRequest request, HttpServletResponse response,
			@RequestBody AuthenticationBean requestBean) {

		return (GeneralWsResponseBean) authenticationService.authenticate(requestBean.getUsername(), requestBean.getPassword(), requestBean.getToken());
	}
}