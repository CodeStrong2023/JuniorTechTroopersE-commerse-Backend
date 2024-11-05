package prueba1.login.utils;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class Mail {

    @Autowired
    private JavaMailSender mailSender;

    @Async  // Ejecuta este método en un hilo separado
    public void sendEmail(String to, String subject, String htmlContent) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            // Usa MimeMessageHelper para configurar el mensaje HTML
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);  // `true` indica que el contenido es HTML
            helper.setFrom("juniorstechtroopers.frsr@gmail.com");

            // Envía el mensaje
            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace(); // Puedes manejar la excepción o lanzar una custom exception
        }
    }


    public String generarTemplate(String usernameToLowerCase, Integer codigoVerificacion){
        StringBuilder mailContent = new StringBuilder();

        mailContent.append("<div style='font-family: Arial, sans-serif; color: #333;'>");
        mailContent.append("<h1 style='color: #4CAF50; text-align: center;'>Validar cuenta</h1>");
        mailContent.append("<p style='font-size: 16px; text-align: center;'>");
        mailContent.append("Bienvenido: ").append(usernameToLowerCase).append(". ¡Gracias por registrarte! Para completar tu registro, utiliza el siguiente código de verificación:");
        mailContent.append("</p>");
        mailContent.append("<p style='font-size: 24px; font-weight: bold; color: #4CAF50; text-align: center; margin: 20px 0;'>");
        mailContent.append(codigoVerificacion);
        mailContent.append("</p>");
        mailContent.append("</div>");

        return mailContent.toString();

    }
    public String mailTicket(String firstname,
                             String lastname,
                             String email,
                             String phone,
                             String nombreHospedaje,
                             Date StartDate,
                             Date EndDate,
                             Double totalPago){
        StringBuilder mailContent = new StringBuilder();


        mailContent.append("<div style='font-family: Arial, sans-serif; color: #333; max-width: 600px; margin: auto; border: 1px solid #ddd; padding: 20px;'>");

        mailContent.append("<h2 style='color: #4CAF50; text-align: center;'>Ticket de Reserva</h2>");
        mailContent.append("<p style='font-size: 14px; text-align: center;'>Detalles de Reserva Hospedaje:</p>");

        mailContent.append("<hr style='border: 0; height: 1px; background-color: #ddd; margin: 20px 0;'>");

// Detalles del cliente
        mailContent.append("<p style='font-size: 14px; text-align: center;> Contacto Reserva </p>");
        mailContent.append("<table style='width: 100%; font-size: 14px; margin-bottom: 20px;'>");

        mailContent.append("<tr><td style='font-weight: bold;'>Nombre y Apellido:</td><td>").append(firstname).append(" ").append(lastname).append(" </td></tr>");
        mailContent.append("<tr><td style='font-weight: bold;'>Email de contacto:</td><td>").append(email).append("</td></tr>");
        mailContent.append("<tr><td style='font-weight: bold;'>Celular:</td><td>").append(phone).append("</td></tr>");
        mailContent.append("</table>");

// Detalles del hospedaje
        mailContent.append("<table style='width: 100%; font-size: 14px; margin-bottom: 20px;'>");
        mailContent.append("<tr><td style='font-weight: bold;'>Hospedaje:</td><td>").append(nombreHospedaje).append("</td></tr>");
        mailContent.append("<tr><td style='font-weight: bold;'>Fecha de inicio:</td><td>").append(StartDate).append("</td></tr>");
        mailContent.append("<tr><td style='font-weight: bold;'>Fecha final:</td><td>").append(EndDate).append("</td></tr>");
        mailContent.append("<tr><td style='font-weight: bold;'>Monto Total:</td><td style='color: #4CAF50; font-weight: bold;'>$").append(totalPago).append("</td></tr>");
        mailContent.append("</table>");

        mailContent.append("<hr style='border: 0; height: 1px; background-color: #ddd; margin: 20px 0;'>");

// Nota de agradecimiento y contacto
        mailContent.append("<p style='font-size: 12px; color: #777; text-align: center;'>");
        mailContent.append("Si tienes alguna consulta sobre tu reserva, no dudes en ponerte en contacto con nosotros.");
        mailContent.append("</p>");

        mailContent.append("</div>");

        return mailContent.toString();

    }
}


