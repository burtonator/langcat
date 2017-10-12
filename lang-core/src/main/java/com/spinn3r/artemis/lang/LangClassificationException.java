/*
 * Copyright 2010 "Tailrank, Inc (Spinn3r)"
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.spinn3r.artemis.lang;

@SuppressWarnings( "serial" )
public class LangClassificationException extends Exception {

    private LangClassificationException(String message) {
        super(message);
    }

    private LangClassificationException(String message, Throwable cause) {
        super( message, cause );
    }

    private LangClassificationException(Throwable cause) {
        super( cause );
    }

    /**
     * Thrown if we are not able to detect a language.
     */
    @SuppressWarnings( "serial" )
    public static class UnknownLangException extends LangClassificationException {

        public UnknownLangException(String message) {
            super( message );
        }

        public UnknownLangException(String message, Throwable cause) {
            super( message, cause );
        }

        public UnknownLangException(Throwable cause) {
            super( cause );
        }

    }

    public static class IncorrectLangException extends LangClassificationException {

        public IncorrectLangException(String message) {
            super(message);
        }

        public IncorrectLangException(String message, Throwable cause) {
            super(message, cause);
        }

        public IncorrectLangException(Throwable cause) {
            super(cause);
        }

    }

    public static class InitFailedException extends LangClassificationException {

        public InitFailedException(String message) {
            super(message);
        }

        public InitFailedException(String message, Throwable cause) {
            super(message, cause);
        }

        public InitFailedException(Throwable cause) {
            super(cause);
        }

    }

    public static class LargeProfileException extends LangClassificationException {

        public LargeProfileException(String message) {
            super(message);
        }

        public LargeProfileException(String message, Throwable cause) {
            super(message, cause);
        }

        public LargeProfileException(Throwable cause) {
            super(cause);
        }

    }

}