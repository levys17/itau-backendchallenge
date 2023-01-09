package itidigital.backendchallenge.exceptionhandler;

import itidigital.backendchallenge.exceptionhandler.exceptions.RuleProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    private static final String GENERAL_MSG_FOR_USER = "An unexpected error occurred";

    @ExceptionHandler(RuleProcessingException.class)
    public ResponseEntity<?> handleRuleProcessing(RuleProcessingException ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionType type = ExceptionType.PROCESSING_ERROR;
        String detail = ex.getMessage();
        ExceptionDetail problem = createExceptionDetailBuilder(status, type, detail).userMessage(detail).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ExceptionType type = ExceptionType.SYSTEM_ERROR;
        String detail = "Unexpected System Error. Please retry later.";
        ExceptionDetail exceptionDetail = createExceptionDetailBuilder(status, type, detail).userMessage(detail).build();
        return handleExceptionInternal(ex, exceptionDetail, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionType type = ExceptionType.INVALID_DATA;
        String detail = "Data of one or more fields are invalid. Verify and retry.";

        BindingResult bindingResult = ex.getBindingResult();
        List<ExceptionDetail.Field> exceptionDetailField = bindingResult.getFieldErrors().stream()
                .map(fieldError -> {
                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                    return ExceptionDetail.Field.builder()
                            .name(fieldError.getField())
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        ExceptionDetail exceptionDetail = createExceptionDetailBuilder(status, type, detail).userMessage(detail).fields(exceptionDetailField).build();

        return super.handleExceptionInternal(ex, exceptionDetail, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionType type = ExceptionType.RESOURCE_NOT_FOUND;
        String detail = String.format("The resource %s that you've tried to access doesn't exists.", ex.getRequestURL());
        ExceptionDetail exceptionDetail = createExceptionDetailBuilder(status, type, detail).userMessage(detail).build();
        return handleExceptionInternal(ex, exceptionDetail, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = ExceptionDetail.builder()
                    .timestamp(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .userMessage(GENERAL_MSG_FOR_USER)
                    .build();
        } else if (body instanceof String) {
            body = ExceptionDetail.builder()
                    .timestamp(LocalDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(GENERAL_MSG_FOR_USER)
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ExceptionDetail.ExceptionDetailBuilder createExceptionDetailBuilder(HttpStatus status, ExceptionType type, String detail) {
        return ExceptionDetail.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(type.getTitle())
                .detail(detail);
    }
}
