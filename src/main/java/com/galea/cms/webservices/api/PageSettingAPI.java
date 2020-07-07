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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.galea.cms.repository.service.AuthenticationService;
import com.galea.cms.repository.service.CommonServiceUtils;
//Services
import com.galea.cms.repository.service.PageSettingService;

//Beans
import com.galea.cms.webservices.bean.GeneralWsResponseBean;

import com.galea.cms.webservices.bean.pagesetting.PageSettingBean;

import com.galea.cms.webservices.bean.DeleteEntityReqBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Page Setting API", description="API Operations for page setting logic")
public class PageSettingAPI {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ResourceBundle labels = ResourceBundle.getBundle("messages");
	
	// Services
	@Autowired
	PageSettingService pageSettingService;

	@Autowired
	AuthenticationService authenticationService;
	// Services

	//Outlets
    @ApiOperation(value = "Get a list of page setting", response = GeneralWsResponseBean.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/getPageList")
	public GeneralWsResponseBean getPageSettingList(HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getHeader("token");
        String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);
		
		if(allowContinue) {
			return (GeneralWsResponseBean) pageSettingService.getAllPageSetting();
		}
		else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
		
	}

    // @ApiOperation(value = "Get page setting by id", response = GeneralWsResponseBean.class)
    // @ApiResponses(value = {
    //     @ApiResponse(code = 200, message = "Successfully Get the details"),
    //     @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    //     @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    //     @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    // })
	// @ResponseBody
	// @RequestMapping(method = RequestMethod.POST, value = "/getPageSettingById", consumes = { "application/json" }, produces = {
	// 		"application/json" })
	// public GeneralWsResponseBean getPageSettingById(HttpServletRequest request, HttpServletResponse response,
	// 		@RequestBody PageSettingBean requestBean) {

	// 	return (GeneralWsResponseBean) pageSettingService.getPageSettingById(requestBean.getSettingId());
	// }

    @ApiOperation(value = "Get page setting by key (Complete with the custom data group, data, setting, and value)", response = GeneralWsResponseBean.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Get the details"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/getPageSettingByKey", consumes = { "application/json" }, produces = {
			"application/json" })
	public GeneralWsResponseBean getPageSetting(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PageSettingBean requestBean) {

		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);
		
		if(allowContinue) {
			return (GeneralWsResponseBean) pageSettingService.getPageSettingByKey(requestBean.getPageKey());
		}
		else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}

	//Public API NO NEED AUTHENTICATION
    @ApiOperation(value = "Get all custom data value by page setting by key ", response = GeneralWsResponseBean.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Get the details"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/getAllValueByPageSettingKey", consumes = { "application/json" }, produces = {
			"application/json" })
	@CrossOrigin(origins = { 
		"http://localhost:8080", 
		"http://localhost:80", 
		"https://localhost:80", 
		"https://www.djw.world", 
		"https://djw.world" })
	public GeneralWsResponseBean getAllValueByPageSettingKey(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PageSettingBean requestBean) {

		return (GeneralWsResponseBean) pageSettingService.getAllValueByPageSettingKey(requestBean.getPageKey());
	}

	//Supposed to be for secure access only
    @ApiOperation(value = "Add page setting", response = GeneralWsResponseBean.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Added"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/addPageSetting", consumes = { "application/json" }, produces = {
			"application/json" })
	public GeneralWsResponseBean addPageSetting(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PageSettingBean requestBean) {

		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);
		
		if(allowContinue) {
			return (GeneralWsResponseBean) pageSettingService.addPageSetting(requestBean);
		}
		else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}

    @ApiOperation(value = "Update page setting / Also can be used to update status to either active, deactive, or deleted", response = GeneralWsResponseBean.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Updated"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/updatePageSetting", consumes = { "application/json" }, produces = {
			"application/json" })
	public GeneralWsResponseBean updatePageSetting(HttpServletRequest request, HttpServletResponse response,
			@RequestBody PageSettingBean requestBean) {

		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);
		
		if(allowContinue) {
			return (GeneralWsResponseBean) pageSettingService.updatePageSetting(requestBean);
		}
		else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}

    @ApiOperation(value = "Delete page setting / Effectively removed it from db", response = GeneralWsResponseBean.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Delete"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/deletePageSetting", consumes = { "application/json" }, produces = {
			"application/json" })
	public GeneralWsResponseBean deletePageSetting(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DeleteEntityReqBean requestBean) {

		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);
		
		if(allowContinue) {
			return (GeneralWsResponseBean) pageSettingService.deletePageSetting(requestBean);
		}
		else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}

	}
}