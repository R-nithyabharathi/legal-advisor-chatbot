package com.legal.chatbot.controller;

import com.legal.chatbot.model.Appointment;
import com.legal.chatbot.repository.AppointmentRepository;
import com.legal.chatbot.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private EmailService emailService;

    // ✅ Show Appointment Page
    @GetMapping("/appointment")
    public String showAppointmentForm(
            @RequestParam(value = "lawyer", required = false) String lawyer,
            Model model) {

        Appointment appointment = new Appointment();

        // Pre-fill lawyer name if passed in URL
        if (lawyer != null) {
            appointment.setLawyerName(lawyer);
        }

        model.addAttribute("appointment", appointment);
        return "appointment";  // appointment.html
    }

    // ✅ Book Appointment
    @PostMapping("/book")
    public String bookAppointment(@ModelAttribute Appointment appointment) {

        try {
            // 🔍 Debug (optional - remove later)
            System.out.println("Email: " + appointment.getEmail());
            System.out.println("Lawyer: " + appointment.getLawyerName());
            System.out.println("Date: " + appointment.getAppointmentDate());
            System.out.println("Time: " + appointment.getTime());

            // ✅ Save to database
            appointmentRepository.save(appointment);

            // ✅ Send confirmation email (FIXED)
            emailService.sendConfirmationEmail(
                    appointment.getEmail(),
                    appointment.getLawyerName(),
                    appointment.getAppointmentDate(),
                    appointment.getTime()
            );

        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // optional error.html
        }

        // ✅ Redirect to success page
        return "redirect:/Success";
    }

    // ✅ Success Page
    @GetMapping("/Success")
    public String successPage() {
        return "Success";  // success.html
    }
}