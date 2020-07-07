package com.galea.cms.webservices.api;

import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//Services
import com.galea.cms.repository.service.CustomDataGroupService;
import com.galea.cms.repository.service.AuthenticationService;
import com.galea.cms.repository.service.CommonServiceUtils;
//Beans
import com.galea.cms.webservices.bean.GeneralWsResponseBean;

import com.galea.cms.webservices.bean.customdatagroup.CustomDataGroupBean;

import com.galea.cms.webservices.bean.DeleteEntityReqBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Custom Data Group API", description = "API Operations for custom data group logic")
public class CustomDataGroupAPI {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ResourceBundle labels = ResourceBundle.getBundle("messages");

	// Services
	@Autowired
	CustomDataGroupService customDataGroupService;

	@Autowired
	AuthenticationService authenticationService;
	// Services

	// Outlets
	// @ApiOperation(value = "Get a list of custom data group", response =
	// GeneralWsResponseBean.class)
	// @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully
	// retrieved list"),
	// @ApiResponse(code = 401, message = "You are not authorized to view the
	// resource"),
	// @ApiResponse(code = 403, message = "Accessing the resource you were trying to
	// reach is forbidden"),
	// @ApiResponse(code = 404, message = "The resource you were trying to reach is
	// not found") })
	// @ResponseBody
	// @RequestMapping(method = RequestMethod.POST, value =
	// "/getcustomDataGroupList")
	// public GeneralWsResponseBean getCustomDataGroupList(HttpServletRequest
	// request, HttpServletResponse response) {

	// String token = request.getHeader("token");
	// String latestToken = authenticationService.getLatestToken();
	// Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);

	// if(allowContinue) {
	// return (GeneralWsResponseBean)
	// customDataGroupService.getAllCustomDataGroup();
	// }
	// else {
	// return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
	// }
	// }

	// get csgroup by page setting id
	@ApiOperation(value = "Get custom data group list by page setting id", response = GeneralWsResponseBean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list by page setting id"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/getCdGroupByPageStgId", consumes = { "application/json" })
	public GeneralWsResponseBean getCdGroupByPageStgId(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CustomDataGroupBean requestBean) {

		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);

		if (allowContinue) {
			return (GeneralWsResponseBean) customDataGroupService.getCdGroupByPageStgId(requestBean.getPageSettingId());
		} else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}
	// get csgroup by page setting id

	// Public API NO NEED AUTHENTICATION
	// get csgroup by page setting key
	@ApiOperation(value = "Get custom data group list by page setting key", response = GeneralWsResponseBean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list by page setting key"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/getCdGroupByPageStgKey", consumes = { "application/json" })
	@CrossOrigin(origins = { 
		"http://localhost:8080", 
		"http://localhost:80", 
		"https://localhost:80", 
		"https://www.djw.world", 
		"https://djw.world" })
	public GeneralWsResponseBean getCdGroupByPageStgKey(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CustomDataGroupBean requestBean) {

		return (GeneralWsResponseBean) customDataGroupService.getCdGroupByPageStgKey(requestBean.getPageSettingKey());
	}
	// get csgroup by page setting id

	// @ApiOperation(value = "Get custom data group by id", response =
	// GeneralWsResponseBean.class)
	// @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Get
	// the details"),
	// @ApiResponse(code = 401, message = "You are not authorized to view the
	// resource"),
	// @ApiResponse(code = 403, message = "Accessing the resource you were trying to
	// reach is forbidden"),
	// @ApiResponse(code = 404, message = "The resource you were trying to reach is
	// not found") })
	// @ResponseBody
	// @RequestMapping(method = RequestMethod.POST, value =
	// "/getCustomDataGroupById", consumes = {
	// "application/json" }, produces = { "application/json" })
	// public GeneralWsResponseBean getCustomDataGroupById(HttpServletRequest
	// request, HttpServletResponse response,
	// @RequestBody CustomDataGroupBean requestBean) {

	// return (GeneralWsResponseBean)
	// customDataGroupService.getCustomDataGroupById(requestBean.getCdGroupId());
	// }

	// Supposed to be for secure access only
	@ApiOperation(value = "Add custom data group", response = GeneralWsResponseBean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Added"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/addCustomDataGroup", consumes = {
			"application/json" }, produces = { "application/json" })
	public GeneralWsResponseBean addCustomDataGroup(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CustomDataGroupBean requestBean) {

		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);

		if (allowContinue) {
			return (GeneralWsResponseBean) customDataGroupService.addCustomDataGroup(requestBean);
		} else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}

	@ApiOperation(value = "Update custom data group / Also can be used to update status to either active, deactive, or deleted", response = GeneralWsResponseBean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Updated"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/updateCustomDataGroup", consumes = {
			"application/json" }, produces = { "application/json" })
	public GeneralWsResponseBean updateCustomDataGroup(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CustomDataGroupBean requestBean) {

		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);

		if (allowContinue) {
			return (GeneralWsResponseBean) customDataGroupService.updateCustomDataGroup(requestBean);
		} else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}

	@ApiOperation(value = "Delete custom data group / Effectively removed it from db", response = GeneralWsResponseBean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Delete"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustomDataGroup", consumes = {
			"application/json" }, produces = { "application/json" })
	public GeneralWsResponseBean deleteCustomDataGroup(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DeleteEntityReqBean requestBean) {

		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);

		if (allowContinue) {
			return (GeneralWsResponseBean) customDataGroupService.deleteCustomDataGroup(requestBean);
		} else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}

	// Public API NO NEED AUTHENTICATION
	// get csgroup by page setting key
	@ApiOperation(value = "Get all custom data value by custom data group id", response = GeneralWsResponseBean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list by cd group id"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/getAllValueByCdGroupId", consumes = { "application/json" })
	@CrossOrigin(origins = { 
		"http://localhost:8080", 
		"http://localhost:80", 
		"https://localhost:80", 
		"https://www.djw.world", 
		"https://djw.world" })
	public GeneralWsResponseBean getAllValueByCdGroupId(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CustomDataGroupBean requestBean) {

		return (GeneralWsResponseBean) customDataGroupService.getAllValueByCdGroupId(requestBean.getCdGroupId());
	}
}