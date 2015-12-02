package baic.obd.protocol.model;

import java.util.ArrayList;
import java.util.List;

public class ModelPackage {
	private String imei;
	private List<Model> modelList = new ArrayList<Model>();

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public List<Model> getModelList() {
		return modelList;
	}

	public void setModelList(List<Model> modelList) {
		this.modelList = modelList;
	}

	@Override
    public String toString() {
	    return "ModelPackage [imei=" + imei + ", modelList=" + modelList + "]";
    }
}
