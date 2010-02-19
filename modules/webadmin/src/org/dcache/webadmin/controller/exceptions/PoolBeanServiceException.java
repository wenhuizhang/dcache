package org.dcache.webadmin.controller.exceptions;

/**
 *
 * @author jan schaefer 29-10-2009
 */
public class PoolBeanServiceException extends Exception {

    /**
     * Constructor with error message and root cause.
     *
     * @param msg
     *            the error message associated with the exception
     * @param cause
     *            the root cause of the exception
     */
    public PoolBeanServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Constructor with error message and root cause.
     *
     * @param cause
     *            the root cause of the exception
     */
    public PoolBeanServiceException(Throwable cause) {
        super(cause);
    }
}
