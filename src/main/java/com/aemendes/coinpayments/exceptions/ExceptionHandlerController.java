package com.aemendes.coinpayments.exceptions;

import com.aemendes.coinpayments.model.ApiError;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    /**
     * Custom Exception to handle Currency Conversion From PathVariables
     * @param conversionFailedException
     * @return ApiError
     */
    @ExceptionHandler(ConversionFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleConversionFailedException(ConversionFailedException conversionFailedException) {
        return ApiError.builder()
                .errorType(ApiError.ErrorType.INVALID_CURRENCY)
                .message("Invalid Currency " + conversionFailedException.getValue())
                .build();
    }
}
