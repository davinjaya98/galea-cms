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
import com.galea.cms.repository.service.QuotationService;
import com.galea.cms.repository.service.AuthenticationService;
import com.galea.cms.repository.service.CommonServiceUtils;
//Beans
import com.galea.cms.webservices.bean.GeneralWsResponseBean;

import com.galea.cms.webservices.bean.quotation.QuotationBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="Quotation API", description="API regarding quotation logic")
public class QuotationAPI {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private ResourceBundle labels = ResourceBundle.getBundle("messages");

    //services
    @Autowired
    QuotationService quotationService;

	@Autowired
	AuthenticationService authenticationService;
    //services

    @ApiOperation(value = "Get list of quotations", response = GeneralWsResponseBean.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/getQuotationList")
    public GeneralWsResponseBean getQuotationList(HttpServletRequest request, HttpServletResponse response) {
		
		String token = request.getHeader("token");
        String latestToken = authenticationService.getLatestToken();
		Boolean allowContinue = CommonAPIUtils.checkToken(latestToken, token);
		
		if(allowContinue) {
            return (GeneralWsResponseBean) quotationService.getAllQuotation();
		}
		else {
			return CommonServiceUtils.generateResponseBeanWithUnauthorizedStatus();
		}
	}

    // @ApiOperation(value = "Get quotation by id", response = GeneralWsResponseBean.class)
    // @ApiResponses(value = {
    //     @ApiResponse(code = 200, message = "Successfully Get the details"),
    //     @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
    //     @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
    //     @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    // })
	// @ResponseBody
	// @RequestMapping(method = RequestMethod.POST, value = "/getQuotationById", consumes = { "application/json" }, produces = {
	// 		"application/json" })
	// public GeneralWsResponseBean getQuotationById(HttpServletRequest request, HttpServletResponse response,
	// 		@RequestBody QuotationBean requestBean) {

	// 	return (GeneralWsResponseBean) quotationService.getQuotationById(requestBean.getQuotationId());
	// }

    @ApiOperation(value = "Add new quotation", response = GeneralWsResponseBean.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully Added"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, value = "/addQuotation", consumes = { "application/json" }, produces = {
			"application/json" })
	public GeneralWsResponseBean addQuotation(HttpServletRequest request, HttpServletResponse response,
			@RequestBody QuotationBean requestBean) {

		return (GeneralWsResponseBean) quotationService.addQuotation(requestBean);
	}
}