/* 
 * RT MAP, Home of Professional MAP 
 * Copyright 2016 Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.rtmap.core.exp;

/**
 * BusinessException.   业务异常
 * 
 * @author Muarine <maoyun@rtmap.com>
 * @date   2016年4月18日
 * @since 1.0.0
 */
public class BusinessException extends RuntimeException{

    /** The serialVersionUID */
    private static final long serialVersionUID = -8891124681707456235L;
    
    private Integer code;
    
    public Integer getCode(){
        return code;
    }
    
    /**
     * Constructs an <code>BusinessException</code> with no
     * detail message.
     */
    public BusinessException() {
        super();
    }

    /**
     * Constructs an <code>BusinessException</code> with the
     * specified detail message.
     *
     * @param   code   the exception code.
     */
    public BusinessException(Integer code) {
        super(code + "");
        this.code = code;
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.
     *
     * <p>Note that the detail message associated with <code>cause</code> is
     * <i>not</i> automatically incorporated in this exception's detail
     * message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link Throwable#getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link Throwable#getCause()} method).  (A <tt>null</tt> value
     *         is permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since 1.5
     */
    public BusinessException(String code, Throwable cause) {
        super(code, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail
     * message of <tt>(cause==null ? null : cause.toString())</tt> (which
     * typically contains the class and detail message of <tt>cause</tt>).
     * This constructor is useful for exceptions that are little more than
     * wrappers for other throwables (for example, {@link
     * java.security.PrivilegedActionException}).
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link Throwable#getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since  1.5
     */
    public BusinessException(Throwable cause) {
        super(cause);
    }
    
}
