package dev.sherwin.HotelReservation.service.impl;

import dev.sherwin.HotelReservation.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendOtp(String email) {
        String otp = generateOtp();
        try {
            sendOtpToMail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("unbale to send otp");
        }
    }

    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = 1000000 + random.nextInt(9000000);
        return String.valueOf(otp);

    }

    private void sendOtpToMail(String email, String otp)
            throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new
                MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Your OTP is : ");
        mimeMessageHelper.setText(otp);
        javaMailSender.send(mimeMessage);
    }
}
