package baic.obd.gateway.controller;

import org.apache.log4j.Logger;

import baic.obd.protocol.model.ModelPackage;

public abstract class BaseController implements Runnable {

	private static final Logger LOG = Logger.getLogger(BaseController.class);

	public abstract boolean persistData();

	protected ModelPackage mp;

	public void run() {
		if (this.persistData()) {
			LOG.info("Data persisted success.");
		} else {
			LOG.error("Data persisted failed.");
		}
	}
}
