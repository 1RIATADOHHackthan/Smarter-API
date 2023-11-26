/**
 * 
 */
package com.hackathon.smarter.controller;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.smarter.externalservice.OneRecordService;
import com.hackathon.smarter.schema.ServiceStatus;
import com.hackathon.smarter.schema.Shipment;
import com.hackathon.smarter.schema.Waybill;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author veera
 *
 */
@Controller("/")
@CrossOrigin
public class BookingController {
	
	@Autowired
	private OneRecordService oneRecordService;

	
	public String createBookingHomePage() {
		return "index";
	}
	
	@PostMapping("/createBooking")
	public Shipment bookingController(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String accessToken = null;
		try {
			accessToken = oneRecordService.getAccessToken();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		Shipment shipmentSO = new  Shipment();
		if(accessToken != null) {
			//JSONObject shp = oneRecordService.callShipmentService(accessToken);
			shipmentSO.setGoodsDescription("TEST Goods ");
			Waybill waybill = new Waybill();
			waybill.setAccountingInformation("Account info");
			shipmentSO.setWaybill(waybill);
			shipmentSO.setErrormsg(accessToken);
		}else {
			shipmentSO.setErrormsg("Authentication is not success");
		}
		
		return shipmentSO;
	}

}
