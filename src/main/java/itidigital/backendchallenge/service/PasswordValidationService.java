package itidigital.backendchallenge.service;


import itidigital.backendchallenge.dto.PasswordValidationResponseDTO;

public interface PasswordValidationService {

    PasswordValidationResponseDTO validatePassword(String password);

    PasswordValidationResponseDTO validatePasswordWithDetails(String password);
}
