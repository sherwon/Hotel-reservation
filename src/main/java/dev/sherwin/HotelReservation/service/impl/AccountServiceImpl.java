package dev.sherwin.HotelReservation.service.impl;

import dev.sherwin.HotelReservation.dto.AuthenticationRequestDto;
import dev.sherwin.HotelReservation.dto.AuthenticationResponseDto;
import dev.sherwin.HotelReservation.dto.RegisterRequestDto;
import dev.sherwin.HotelReservation.entity.Account;
import dev.sherwin.HotelReservation.mapper.RegisterRequestMapper;
import dev.sherwin.HotelReservation.repository.AccountRepository;
import dev.sherwin.HotelReservation.service.AccountService;
import dev.sherwin.HotelReservation.service.JwtService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final JavaMailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public RegisterRequestDto register(RegisterRequestDto registerRequestDto) {
        var account = RegisterRequestMapper.mapper(registerRequestDto);
        String password = passwordEncoder.encode(account.getPassword());
        account.setPassword(password);
        var saveAccount = accountRepository.save(account);
        return RegisterRequestMapper.mapper(saveAccount);
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        //FirstStep
        //We need to validate our request (validate whether password & username is correct)
        //Verify whether user present in the database
        //Which AuthenticationProvider -> DaoAuthenticationProvider (Inject)
        //We need to authenticate using authenticationManager injecting this authenticationProvider
        //SecondStep
        //Verify whether userName and password is correct => UserNamePasswordAuthenticationToken
        //Verify whether user present in db
        //generateToken
        //Return the token
        var user = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder().accessToken(jwtToken).build();

    }
}
