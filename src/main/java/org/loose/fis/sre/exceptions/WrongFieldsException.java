package org.loose.fis.sre.exceptions;

public class WrongFieldsException extends Exception {
        public WrongFieldsException() {
            super(String.format("The filds does not match"));
        }
}
