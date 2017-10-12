package com.spinn3r.artemis.lang;

/**
 *
 */
@SuppressWarnings( "serial" )
public class InsufficientLengthException extends LangClassificationException.UnknownLangException {

    public InsufficientLengthException(String message) {
        super( message );
    }

    public InsufficientLengthException(String message, Throwable cause) {
        super( message, cause );
    }

    public InsufficientLengthException(Throwable cause) {
        super( cause );
    }

}
