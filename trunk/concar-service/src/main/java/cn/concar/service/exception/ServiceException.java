package cn.concar.service.exception;

public class ServiceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8727135069045802601L;
	
	private Reason reason;
	
	public static enum Reason {DUPLICATE_IDENTITY, DUPLICATE_WORK_ID, DUPLICATE, RELATE, ALREADY_BIND, NO_DEV_FOUND, ALREADY_REGISTER, NO_BIND_VEH};

	/**
     * Creates a new instance of ServiceException.
     *
     * @param errorMessage errorMessage
     */
    public ServiceException(String errorMessage) {
        super(errorMessage);
    }
    
    public ServiceException(String errorMessage, Reason reason) {
        super(errorMessage);
        this.reason = reason;
    }
   
    /**
     * Creates a new instance of ServiceException.
     *
     * @param errorMessage errorMessage
     * @param error error
     */
    public ServiceException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
    
    public Reason getReason() {
    	return reason;
    }
}
