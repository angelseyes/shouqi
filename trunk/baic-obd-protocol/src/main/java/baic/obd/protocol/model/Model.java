package baic.obd.protocol.model;

public class Model {
	private Head head = new Head();
	private Body body = new Body();

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	@Override
    public String toString() {
	    return "Model [head=" + head.toString() + ", body=" + body.toString() + "]";
    }
}
