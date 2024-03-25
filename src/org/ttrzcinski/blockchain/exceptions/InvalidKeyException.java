package org.ttrzcinski.blockchain.exceptions;

/**
 * Represents an event, when there was a try to use wrong key, which didn't match.
 */
public class InvalidKeyException extends RuntimeException {

    /**
     * Rises an exception and passes it.
     *
     * @param reason described message to log about factor, which resulted in that exception
     */
    public InvalidKeyException(String reason) {
        super(reason);
    }

}