package itidigital.backendchallenge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Password Input")
public class PasswordInputDTO {

    @NotNull
    @ApiModelProperty(required = true)
    private String password;
}
