package dev.sherwin.HotelReservation.mapper;

import dev.sherwin.HotelReservation.dto.RegisterRequestDto;
import dev.sherwin.HotelReservation.entity.Account;

public class RegisterRequestMapper {
    public static Account mapper(RegisterRequestDto registerRequestDto) {
        return Account.builder()
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .age(registerRequestDto.getAge())
                .role(registerRequestDto.getRole())
                .email(registerRequestDto.getEmail())
                .password(registerRequestDto.getPassword())
                .build();
    }

    public static RegisterRequestDto mapper(Account account) {
        return RegisterRequestDto.builder()
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .age(account.getAge())
                .role(account.getRole())
                .email(account.getEmail())
                .password(account.getPassword())
                .build();
    }
}
