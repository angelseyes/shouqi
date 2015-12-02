package cn.concar.service.exception;

/**
 * ClassName: ServicePersistenceException
 * Function: Exception of encode failure.
 * Date: 2013-6-24
 *
 * @author haoli
 * @version 1.0
 */
public class ServicePersistenceException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5520395074269656066L;

	/**
     * Creates a new instance of ServicePersistenceException.
     *
     * @param errorMessage errorMessage
     */
    public ServicePersistenceException(String errorMessage) {
        super(errorMessage);
    }
   
    /**
     * Creates a new instance of ServicePersistenceException.
     *
     * @param errorMessage errorMessage
     * @param error error
     */
    public ServicePersistenceException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
