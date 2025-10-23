package poly.edu.asm.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    // simple in-memory queue
    private final List<Mail> queue = new ArrayList<>();

    @Override
    public synchronized void push(Mail mail) {
        queue.add(mail);
    }

    @Override
    public synchronized void send(Mail mail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

            // 1. From
            if (!isNullOrEmpty(mail.getFrom())) {
                helper.setFrom(mail.getFrom());
                helper.setReplyTo(mail.getFrom());
            }

            // 2. To
            if (isNullOrEmpty(mail.getTo())) {
                throw new IllegalArgumentException("Người nhận (To) không được để trống!");
            }
            helper.setTo(mail.getTo().split("[,;]+"));

            // 3. CC / BCC
            if (!isNullOrEmpty(mail.getCc())) {
                helper.setCc(mail.getCc().split("[,;]+"));
            }
            if (!isNullOrEmpty(mail.getBcc())) {
                helper.setBcc(mail.getBcc().split("[,;]+"));
            }

            // 4. Subject & Body
            helper.setSubject(mail.getSubject() != null ? mail.getSubject() : "(Không có tiêu đề)");
            helper.setText(mail.getBody() != null ? mail.getBody() : "", true);

            // 5. Attachments
            if (!isNullOrEmpty(mail.getFilenames())) {
                for (String fn : mail.getFilenames().split("[,;]+")) {
                    File file = new File(fn.trim());
                    if (file.exists() && file.isFile()) {
                        helper.addAttachment(file.getName(), new FileSystemResource(file));
                    }
                }
            }

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi gửi mail: " + e.getMessage(), e);
        }
    }


    private boolean isNullOrEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    // scheduled background worker: every 500ms check the queue
    @Scheduled(fixedDelay = 500)
    public void run() {
        while (true) {
            Mail mail = null;
            synchronized (this) {
                if (!queue.isEmpty()) {
                    mail = queue.remove(0);
                } else {
                    break;
                }
            }
            try {
                if (mail != null) {
                    send(mail);
                    System.out.println("Sent mail to: " + mail.getTo());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

