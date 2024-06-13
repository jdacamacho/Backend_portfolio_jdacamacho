package com.jdacamacho.hexagonal.Application.Output;

public interface ExceptionFormatterIntPort {
    public void responseBusinessRuleViolates(String message);
    public void responseEntityNotFound(String message);
    public void responseEntityExists(String message);
    public void responseNoData(String message);
}
