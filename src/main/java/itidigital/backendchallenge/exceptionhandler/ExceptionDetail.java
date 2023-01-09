package itidigital.backendchallenge.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@ApiModel("Exceptions Details")
public class ExceptionDetail {

    @ApiModelProperty(example = "400")
    private Integer status;

    @ApiModelProperty(example = "Invalid Input")
    private String title;

    @ApiModelProperty(example = "Data of one or more fields are invalid. Verify and retry.")
    private String detail;

    @ApiModelProperty(example = "Data of one or more fields are invalid. Verify and retry.")
    private String userMessage;

    @ApiModelProperty(example = "2023-01-07T18:09:02.70844Z")
    private LocalDateTime timestamp;

    @ApiModelProperty(value = "List of fields with that causes error(optional)")
    private List<Field> fields;

    @ApiModel(value = "Error Fields")
    @Getter
    @Builder
    public static class Field {

        @ApiModelProperty(example = "password")
        private String name;

        @ApiModelProperty(example = "password is required")
        private String userMessage;
    }
}
