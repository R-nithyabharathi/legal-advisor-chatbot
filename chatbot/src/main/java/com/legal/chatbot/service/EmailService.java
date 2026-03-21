package com.legal.chatbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendConfirmationEmail(String toEmail,
                                      String lawyerName,
                                      String appointmentDate,
                                      String appointmentTime) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(toEmail);
            message.setSubject("Appointment Confirmation - Legal Advisor");

            message.setText(
                    "Dear Client,\n\n" +
                    "Your appointment has been successfully booked.\n\n" +
                    "--------------------------------------\n" +
                    "Lawyer Name     : " + lawyerName + "\n" +
                    "Appointment Date: " + appointmentDate + "\n" +
                    "Appointment Time: " + appointmentTime + "\n" +
                    "--------------------------------------\n\n" +
                    "Our team will contact you soon.\n\n" +
                    "Thank you,\n" +
                    "Legal Advisor Team"
            );

            mailSender.send(message);

            System.out.println("✅ Email Sent Successfully");

        } catch (Exception e) {
            System.out.println("❌ Email Error: " + e.getMessage());
        }
    }
}