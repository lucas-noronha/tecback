package br.com.fujideia.iesp.tecback.service;

import javax.mail.*;
import javax.mail.internet.*;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.fujideia.iesp.tecback.DTO.MailConfig;

import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

@AllArgsConstructor
@Service
public class MailService {

    public void EnviarConfirmacaoEmail(Integer userId, String userName, String userEmail) {
        try {

            Session session = CriarSessao();

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lucasnoronha1610@hotmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("Confirmação de Email");
            message.setText("Olá " + userName
                    + ", Click no link abaixo para confirmar seu email \n http://localhost:8080/usuario/confirmacao/"
                    + userId, "utf-8");

            Transport.send(message);

        } catch (Exception e) {
            String passouAqui = "teste";
        }

    }

    private Session CriarSessao() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        var config = ReadJsonConfig();
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(config.email, config.password);
            }
        };
        Session session = Session.getInstance(props, authenticator);
        return session;
    }

    private MailConfig ReadJsonConfig() {
        try (Reader reader = new FileReader(
                "C:\\Users\\lucas\\Desktop\\Faculdade\\Técnologia Para Back End\\MailConfig.txt")) {
            Gson gson = new Gson();
            MailConfig[] features = gson.fromJson(reader, MailConfig[].class);

            return features[0];

        } catch (Exception e) {
            return null;
        }
    }

}
