package com.dev.withpet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class EtAuthException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6207904143900656070L;

	public EtAuthException(String message) {
        super(message);
    }
}
