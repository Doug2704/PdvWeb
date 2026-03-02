package com.pdv.exception;

public class ValorNegationException extends RuntimeException {
    public ValorNegationException(String objeto, Double valor ) {
        super(objeto + "não pode ter negativo: " + valor);
    }
}
