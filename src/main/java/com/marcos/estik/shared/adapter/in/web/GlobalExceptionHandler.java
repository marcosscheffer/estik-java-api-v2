package com.marcos.estik.shared.adapter.in.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestNotUsableException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.marcos.estik.auth.domain.exception.InvalidCredentialsException;
import com.marcos.estik.auth.domain.exception.InvalidRefreshTokenException;
import com.marcos.estik.auth.domain.exception.UserInactiveException;
import com.marcos.estik.chat.domain.exception.ChatForbiddenException;
import com.marcos.estik.files.domain.exception.FileMimeTypeInvalidException;
import com.marcos.estik.item.domain.exception.ItemMinimumInvalidException;
import com.marcos.estik.notification.domain.exception.NotificationNotBelongException;
import com.marcos.estik.notification.domain.exception.NotificationNotFoundException;
import com.marcos.estik.shared.domain.exception.DomainCannotMovedException;
import com.marcos.estik.stock.domain.exception.QuantityMovementInvalidException;
import com.marcos.estik.ticket.domain.exception.ForwardedRoleInvalidException;
import com.marcos.estik.ticket.domain.exception.InvalidTicketStatusException;
import com.marcos.estik.ticket.domain.exception.TicketAlreadyAssignedException;
import com.marcos.estik.ticket.domain.exception.TicketMessageInvalidException;
import com.marcos.estik.ticket.domain.exception.TicketNotAssignableException;
import com.marcos.estik.ticket.domain.exception.TicketOwnerShipException;
import com.marcos.estik.user.domain.exception.CannotAssignSelfException;
import com.marcos.estik.user.domain.exception.CannotDeactiveSelfException;
import com.marcos.estik.user.domain.exception.UserOwnershipException;
import com.marcos.estik.user.domain.exception.UsernameAlreadyUseException;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
@RestControllerAdvice 
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(
        InvalidCredentialsException exception, 
        HttpServletRequest request
    ) {
        return build(HttpStatus.UNAUTHORIZED, exception.getMessage(), request);
    }
    
    @ExceptionHandler(UserInactiveException.class)
    public ResponseEntity<ErrorResponse> handleUserInactive(
        UserInactiveException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.UNAUTHORIZED, exception.getMessage(), request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(
        EntityNotFoundException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(
        HttpMessageNotReadableException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValid (
        MethodArgumentNotValidException exception,
        HttpServletRequest request
    ) {
        Map<String, String> fields = new LinkedHashMap<>();
        exception
            .getBindingResult()
            .getFieldErrors()
            .forEach(e -> fields.putIfAbsent(e.getField(), e.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                    ErrorResponse.of(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        "Invalid fields",
                        request.getRequestURI(),
                        fields
                    )
                );
    }

    @ExceptionHandler(UsernameAlreadyUseException.class)
    public ResponseEntity<ErrorResponse> handlerUsernameAlreadyUse (
        UsernameAlreadyUseException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), request);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException exception,
        HttpServletRequest request
    ) {
        String message = "Invalid value for the parameter '" + exception.getName() + "': " + exception.getValue();
        return build(HttpStatus.BAD_REQUEST, message, request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupported(
        HttpRequestMethodNotSupportedException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoResourceFound(
        NoResourceFoundException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    @ExceptionHandler(InvalidTicketStatusException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTicketStatus(
        InvalidTicketStatusException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(TicketAlreadyAssignedException.class)
    public ResponseEntity<ErrorResponse> handleTicketAlreadyAssigned(
        TicketAlreadyAssignedException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(TicketNotAssignableException.class)
    public ResponseEntity<ErrorResponse> handleTicketNotAssignable(
        TicketNotAssignableException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(TicketOwnerShipException.class)
    public ResponseEntity<ErrorResponse> handleTicketOwnerShipException(
        TicketOwnerShipException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.FORBIDDEN, exception.getMessage(), request);
    }

    @ExceptionHandler(DomainCannotMovedException.class)
    public ResponseEntity<ErrorResponse> handleDomainCannotMoved(
        DomainCannotMovedException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(CannotAssignSelfException.class)
    public ResponseEntity<ErrorResponse> handleCannotAssignSelf(
        CannotAssignSelfException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(CannotDeactiveSelfException.class)
    public ResponseEntity<ErrorResponse> handleCannotDeactiveSelf(
        CannotDeactiveSelfException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(UserOwnershipException.class)
    public ResponseEntity<ErrorResponse> handleUserOwnership(
        UserOwnershipException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.FORBIDDEN, exception.getMessage(), request);
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRefreshToken(
        InvalidRefreshTokenException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.UNAUTHORIZED, exception.getMessage(), request);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwt(
        ExpiredJwtException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.UNAUTHORIZED, exception.getMessage(), request);
    }

    @ExceptionHandler(ItemMinimumInvalidException.class)
    public ResponseEntity<ErrorResponse> handleItemMinimumInvalid(
        ItemMinimumInvalidException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler(QuantityMovementInvalidException.class)
    public ResponseEntity<ErrorResponse> handleQuantityMovementInvalid(
        QuantityMovementInvalidException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(TicketMessageInvalidException.class)
    public ResponseEntity<ErrorResponse> handleTicketMessageInvalid(
        TicketMessageInvalidException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotificationNotFound(
        NotificationNotFoundException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    @ExceptionHandler(NotificationNotBelongException.class)
    public ResponseEntity<ErrorResponse> handleNotificationNotBelong(
        NotificationNotBelongException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.FORBIDDEN, exception.getMessage(), request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
        IllegalArgumentException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleObjectOptimisticLockingFailure(
        ObjectOptimisticLockingFailureException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(
        DataIntegrityViolationException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(ForwardedRoleInvalidException.class)
    public ResponseEntity<ErrorResponse> handleForwardedRoleInvalid(
        ForwardedRoleInvalidException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Void> handleAsyncRequestTimeout() {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(AsyncRequestNotUsableException.class)
    public ResponseEntity<Void> handleAsyncRequestNotUsable() {
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(FileMimeTypeInvalidException.class)
    public ResponseEntity<ErrorResponse> handleFileMimeTypeInvalid(
        FileMimeTypeInvalidException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    @ExceptionHandler(ChatForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleChatForbidden(
        ChatForbiddenException exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.FORBIDDEN, exception.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
        Exception exception,
        HttpServletRequest request
    ) {
        return build(HttpStatus.BAD_GATEWAY, exception.getMessage(), request);
    }


    private ResponseEntity<ErrorResponse> build(
        HttpStatus status, 
        String message,
        HttpServletRequest request
    ) {
        return ResponseEntity.status(status)
            .body(
                ErrorResponse.of(
                    status.value(), 
                    status.getReasonPhrase(), 
                    message, 
                    request.getRequestURI()
                )
            );
    }

}
