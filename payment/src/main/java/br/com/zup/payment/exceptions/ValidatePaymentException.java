package br.com.zup.payment.exceptions;

public class ValidatePaymentException extends RuntimeException {
    public ValidatePaymentException(String msg) {
        super(msg);
    }
}
