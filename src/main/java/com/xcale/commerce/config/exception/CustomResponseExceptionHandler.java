package com.xcale.commerce.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class CustomResponseExceptionHandler {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BodyErrorResponse handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        String message = String.format("El parámetro '%s' está faltando", ex.getParameterName());
        return new BodyErrorResponse(HttpStatus.BAD_REQUEST, message, ex);
    }

    @ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BodyErrorResponse handleUnsatisfiedServletRequestParameter(UnsatisfiedServletRequestParameterException ex) {
        StringBuilder builder = new StringBuilder("Los parámetros requeridos son ");
        Arrays.stream(ex.getParamConditions()).forEach(s -> builder.append(s).append(", "));
        String message = builder.substring(0, builder.length() - 2);
        return new BodyErrorResponse(HttpStatus.BAD_REQUEST, message, ex);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    protected BodyErrorResponse handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        String message = String.format("El método '%s' no es soportado", ex.getMethod());
        return new BodyErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, message, ex);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    protected BodyErrorResponse handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        String error = String.format("Media type '%s' no es soportado", ex.getContentType());
        return new BodyErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, error, ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BodyErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String message = "Error de validaciones";
        BodyErrorResponse bodyErrorResponse = new BodyErrorResponse(HttpStatus.BAD_REQUEST, message, ex);
        bodyErrorResponse.addValidationErrors(ex.getBindingResult().getFieldErrors());
        bodyErrorResponse.addValidationError(ex.getBindingResult().getGlobalErrors());
        return bodyErrorResponse;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BodyErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String error = "Mensaje JSON de entrada malformado";
        return new BodyErrorResponse(HttpStatus.BAD_REQUEST, error, ex);
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected BodyErrorResponse handleHttpMessageNotWritable(HttpMessageNotWritableException ex) {
        String error = "Error en el mensaje JSON de salida";
        return new BodyErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, error, ex);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BodyErrorResponse handleNoHandlerFoundException(NoHandlerFoundException ex) {
        String message = String.format("No se pudo encontrar el método '%s' para la URL '%s'",
                ex.getHttpMethod(), ex.getRequestURL());
        return new BodyErrorResponse(HttpStatus.BAD_REQUEST, message, ex);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BodyErrorResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = String.format("El parámetro '%s' de valor '%s' no puede ser convertido al tipo '%s'",
                ex.getName(), ex.getValue(), ex.getRequiredType());
        return new BodyErrorResponse(HttpStatus.BAD_REQUEST, message, ex);
    }

    @ExceptionHandler(CustomErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected BodyErrorResponse handleCustomError(CustomErrorException ex) {
        return new BodyErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }

    @ExceptionHandler(DataModelNoContentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected BodyErrorResponse handleDataModelNoContent(DataModelNoContentException ex) {
        return new BodyErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }

    @ExceptionHandler(HttpFailedException.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    protected BodyErrorResponse handleHttpFailed(HttpFailedException ex) {
        return new BodyErrorResponse(HttpStatus.REQUEST_TIMEOUT, ex.getMessage(), ex);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected BodyErrorResponse handleNotFound(NotFoundException ex) {
        return new BodyErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
    }
}