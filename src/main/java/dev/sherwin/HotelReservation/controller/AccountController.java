package dev.sherwin.HotelReservation.controller;

import dev.sherwin.HotelReservation.dto.AuthenticationRequestDto;
import dev.sherwin.HotelReservation.dto.AuthenticationResponseDto;
import dev.sherwin.HotelReservation.dto.RegisterRequestDto;
import dev.sherwin.HotelReservation.entity.Account;
import dev.sherwin.HotelReservation.service.AccountService;
import dev.sherwin.HotelReservation.service.MailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/api/v1/user/account/")
public class AccountController {
    private final AccountService accountService;
    private final MailService mailService;

    @GetMapping("test")
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("test");
    }

    @PostMapping("register")
    public ResponseEntity<RegisterRequestDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto) {

        return new ResponseEntity<>(accountService.register(registerRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("{email}")
    public String mailOtp(@PathVariable("email") String email) {
        mailService.sendOtp(email);
        return "otp send successfully";
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody AuthenticationRequestDto res) {
        return new ResponseEntity<>(accountService.authenticate(res), HttpStatus.ACCEPTED);
    }

}
