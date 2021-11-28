package com.jv6d1.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.security.auth.login.AccountNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jv6d1.entity.Account;
import com.jv6d1.sbmail.MyConstants;
import com.jv6d1.service.AccountService;
import com.jv6d1.utils.Utility;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/forgot_password")
	public String showForgortPassword() {
		return "/forgotPassword/forgot_password_form";
	}
	
	public void sendEmail(String recipienEmail,String link)
			throws MessagingException, UnsupportedEncodingException{
		 MimeMessage message = mailSender.createMimeMessage();
		 MimeMessageHelper helper= new MimeMessageHelper(message);
		 
				 helper.setFrom(MyConstants.MY_EMAIL);
				 helper.setTo(recipienEmail);
		     
		    String subject = "Here's the link to reset your password";
		     
		    String content = "<p>Hello,</p>"
		            + "<p>You have requested to reset your password.</p>"
		            + "<p>Click the link below to change your password:</p>"
		            + "<p><a href=\"" + link + "\">Change my password</a></p>"
		            + "<br>"
		            + "<p>Ignore this email if you do remember your password, "
		            + "or you have not made the request.</p>";
		     
		    helper.setSubject(subject);
		     
		    helper.setText(content, true);
		     
		    mailSender.send(message);
    } 
	
	@PostMapping("/forgot_password")
	public String processPassword(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		String token = RandomString.make(30);
		try {
			accountService.updateResetPassWordToken(token, email);
			String resetPasswordLink = Utility.getSizeUrl(request)+ "/reset_password?token=" + token;
			sendEmail(email,resetPasswordLink);
			model.addAttribute("message","We have sent a reset password link to your email. Please check.");
		} catch (AccountNotFoundException e) {
			model.addAttribute("error",e.getMessage());
		}catch(UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error","Error while sending email");
		}
		return "/forgotPassword/forgot_password_form";
	}
	
	public void sendEmail() {
		
	}
	
	@GetMapping("/reset_password")
	public String showResetPassword(@RequestParam("token") String token,
			Model model) {
		Account account = accountService.getByResetPasswordToken(token);
		model.addAttribute("token",token);
		model.addAttribute("title", "Reset your password");
		if(account==null) {
			
			model.addAttribute("message","Invalid Token");
			return "/forgotPassword/message";
		}
		return "/forgotPassword/reset_password_form";
	}
	
	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request
			,@Validated @RequestParam("password") String password, Model model) {
	    String token = request.getParameter("token");
	     
	    Account account = accountService.getByResetPasswordToken(token);
	    model.addAttribute("title", "Reset your password");
	     
	    if (account == null) {
	        model.addAttribute("message", "Invalid Token");
	        return "/forgotPassword/message";
	    } else {           
	        accountService.updatePassword(account, password);
	         
	        model.addAttribute("message", "You have successfully changed your password.");
	    }
	     
	    return "/forgotPassword/message";
	}
}
