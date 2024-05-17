package dev.sherwin.HotelReservation.service;

import dev.sherwin.HotelReservation.dto.AuthenticationRequestDto;
import dev.sherwin.HotelReservation.dto.AuthenticationResponseDto;
import dev.sherwin.HotelReservation.dto.RegisterRequestDto;
import dev.sherwin.HotelReservation.entity.Account;

public interface AccountService {
    RegisterRequestDto register(RegisterRequestDto registerRequestDto);

    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);

}
