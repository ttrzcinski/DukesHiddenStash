package org.ttrzcinski.blockchain.exceptions;

/**
 * Represents an event, when there was a try to call for undefined or wrongly named operation.
 */
public class UnknownOperationException extends RuntimeException {

    /**
     * Rises an exception and passes it.
     *
     * @param reason described message to log about factor, which resulted in that exception
     */
    public UnknownOperationException(String reason) {
        super(reason);
    }

}