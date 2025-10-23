package poly.edu.asm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.asm.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    // Hiển thị form
    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("mail", new MailService.Mail());
        return "mail/form";
    }

    // Xử lý form (1 form, 2 button: push / sendNow)
    @PostMapping("/send")
    public String handleMail(@ModelAttribute MailService.Mail mail,
                             @RequestParam String action, Model model) {
        if ("push".equals(action)) {
            mailService.push(mail);
            model.addAttribute("message", "Mail của bạn đã được xếp vào hàng đợi");
        } else if ("sendNow".equals(action)) {
            try {
                mailService.send(mail);
                model.addAttribute("message", "Mail đã được gửi ngay");
            } catch (Exception e) {
                model.addAttribute("message", "Lỗi: " + e.getMessage());
            }
        }
        return "mail/result";
    }

    // Quick endpoint demo
    @ResponseBody
    @GetMapping("/sendQuick")
    public String sendQuick() {
        mailService.push("receiver@gmail.com", "Subject", "Body");
        return "Mail của bạn đã được xếp vào hàng đợi";
    }
}
