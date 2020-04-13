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
import com.galea.cms.repository.service.CustomDataSettingService;
import com.galea.cms.repository.service.AuthenticationService;
import com.galea.cms.repository.service.CommonServiceUtils;
//Beans
import com.galea.cms.webservices.bean.GeneralWsResponseBean;
import com.galea.cms.webservices.bean.DeleteEntityReqBean;
import com.galea.cms.webservices.bean.customdatasetting.CustomDataSettingBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Custom Data Setting API", description = "API Operations for custom data setting logic")
public class CustomDataSettingAPI {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private ResourceBundle labels = ResourceBundle.getBundle("messages");
	
	// Services
	@Autowired
	CustomDataSettingService customDataSettingService;

	@Autowired
	AuthenticationService authenticationService;
	// Services

	// Custom Data List
	// @ApiOperation(value = "Get list of custom data setting by custom data id",
	// response = GeneralWsResponseBean.class)
	// @ApiResponses(value = {
	// @ApiResponse(code = 200, message = "Successfully retrieved list"),
	// @ApiResponse(code = 401, message = "You are not authorized to view the
	// resource"),
	// @ApiResponse(code = 403, message = "Accessing the resource you were trying to
	// reach is forbidden"),
	// @ApiResponse(code = 404, message = "The resource you were trying to reach is
	// not found")
	// })
	// @ResponseBody
	// @RequestMapping(method = RequestMethod.POST, value = "/getCustomDataList")
	// public GeneralWsResponseBean getCustomDataList(HttpServletRequest request,
	// HttpServletResponse response) {

	// return (GeneralWsResponseBean) customDataService.getAllCustomData();
	// }

	// get custom data by id
	// @ApiOperation(value = "Get custom data by id", response =
	// GeneralWsResponseBean.class)
	// @ApiResponses(value = {
	// @ApiResponse(code = 200, message = "Successfully Get the details"),
	// @ApiResponse(code = 401, message = "You are not authorized to view the
	// resource"),
	// @ApiResponse(code = 403, message = "Accessing the resource you were trying to
	// reach is forbidden"),
	// @ApiResponse(code = 404, message = "The resource you were trying to reach is
	// not found")
	// })
	// @ResponseBody
	// @RequestMapping(method = RequestMethod.POST, value = "/getCustomDataById",
	// consumes = { "application/json" }, produces = {
	// "application/json" })
	// public GeneralWsResponseBean getCustomDataById(HttpServletRequest request,
	// HttpServletResponse response,
	// @RequestBody CustomDataBean requestBean) {

	// return (GeneralWsResponseBean)
	// customDataService.getCustomDataById(requestBean.getCdId());
	// }

	// get list of custom data settings by custom data id
	@ApiOperation(value = "Get list of custom data settings by custom data id", response = GeneralWsResponseBean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/getCdSettingsListByCdId", consumes = { "application/json" })
	public GeneralWsResponseBean getCustomDataListByPsId(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CustomDataSettingBean requestBean) {
		
		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);
		
		if(allowContinue) {
			return (GeneralWsResponseBean) customDataSettingService.getCdSettingsListByCdId(requestBean.getCdId());
		}
		else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}

	}

	// get custom data setting by id
	// @ApiOperation(value = "Get custom data setting by id", response = GeneralWsResponseBean.class)
	// @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Get the details"),
	// 		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	// 		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	// 		@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	// @ResponseBody
	// @RequestMapping(method = RequestMethod.POST, value = "/getCdSettingsById", consumes = {
	// 		"application/json" }, produces = { "application/json" })
	// public GeneralWsResponseBean getCdSettingsById(HttpServletRequest request, HttpServletResponse response,
	// 		@RequestBody CustomDataSettingBean requestBean) {

	// 	return (GeneralWsResponseBean) customDataSettingService.getCdSettingsById(requestBean.getCdsId());
	// }

	// Supposed to be for secure access only
	@ApiOperation(value = "Add new custom data setting", response = GeneralWsResponseBean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Added"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/addCustomDataSetting", consumes = {
			"application/json" }, produces = { "application/json" })
	public GeneralWsResponseBean addCustomDataSetting(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CustomDataSettingBean requestBean) {
		
		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);
		
		if(allowContinue) {
			return (GeneralWsResponseBean) customDataSettingService.addCustomDataSetting(requestBean);
		}
		else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}

	@ApiOperation(value = "Update custom data setting", response = GeneralWsResponseBean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Updated"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/updateCustomDataSetting", consumes = {
			"application/json" }, produces = { "application/json" })
	public GeneralWsResponseBean updateCustomDataSetting(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CustomDataSettingBean requestBean) {
		
		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);
		
		if(allowContinue) {
			return (GeneralWsResponseBean) customDataSettingService.updateCustomDataSetting(requestBean);
		}
		else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}

	@ApiOperation(value = "Delete custom data / Effectively removed it from db", response = GeneralWsResponseBean.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Delete"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach isnot found") })

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/deleteCustomDataSetting", consumes = {
			"application/json" }, produces = { "application/json" })
	public GeneralWsResponseBean deleteCustomDataSetting(HttpServletRequest request, HttpServletResponse response,
			@RequestBody DeleteEntityReqBean requestBean) {
		
		String token = request.getHeader("token");
		String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);
		
		if(allowContinue) {
			return (GeneralWsResponseBean) customDataSettingService.deleteCustomDataSetting(requestBean);
		}
		else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}
}
