package cn.concar.ws.service;

import javax.jws.WebService;

@WebService
public interface IMsGateway {

	public String retrieve(String imei);
}
