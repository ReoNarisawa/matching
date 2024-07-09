package com.example.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.ChatGroup;
import com.example.entity.ChatMessage;
import com.example.entity.Companies;
import com.example.entity.MessageDto;
import com.example.entity.Users;
import com.example.service.ChatGroupService;
import com.example.service.ChatService;
import com.example.service.CompanyService;
import com.example.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatController {

	@Autowired
	private ChatService chatService;

	@Autowired
	private ChatGroupService chatGroupService;

	@Autowired
	private UserService userService;

	@Autowired
	private CompanyService companyService;

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public MessageDto sendMessage(MessageDto messageDto) {
		ChatMessage chatMessage = new ChatMessage();
		chatMessage.setContent(messageDto.getContent());
		chatMessage.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		chatMessage.setChatGroupId(messageDto.getChatGroupId());

		// Set sender information
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()
				&& authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			chatMessage.setSender(userDetails.getUsername());
			messageDto.setSender(userDetails.getUsername());

			// Determine the recipient
			ChatGroup chatGroup = chatGroupService.findById(messageDto.getChatGroupId());
			if (chatGroup != null) {
				if (userDetails.getAuthorities().stream()
						.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_COMPANY"))) {
					// Sender is a company, so recipient is a user
					String recipientEmail = userService.findEmailById(chatGroup.getUserId());
					messageDto.setRecipient(recipientEmail);
					chatMessage.setRecipient(recipientEmail);
				} else {
					// Sender is a user, so recipient is a company
					String recipientEmail = companyService.findEmailById(chatGroup.getCompanyId());
					messageDto.setRecipient(recipientEmail);
					chatMessage.setRecipient(recipientEmail);
				}
			}
		}

		chatService.save(chatMessage);
		messageDto.setTimestamp(chatMessage.getTimestamp());
		return messageDto;
	}

	@GetMapping("/chat")
	public String chat(@RequestParam("chatGroupId") int chatGroupId, Model model, HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()
				&& authentication.getPrincipal() instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			session.setAttribute("user", userDetails);
			model.addAttribute("userEmail", userDetails.getUsername());

			List<ChatMessage> chatMessages = chatService.findChatMessagesByChatGroupId(chatGroupId);
			model.addAttribute("chatMessages", chatMessages);
			model.addAttribute("chatGroupId", chatGroupId);

			// 相手の名前を取得してモデルに追加
			ChatGroup chatGroup = chatGroupService.findById(chatGroupId);
			String recipientName = "";

			if (chatGroup != null) {
				if (userDetails.getAuthorities().stream()
						.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("COMPANY"))) {
					// ログインユーザーが会社の場合、ユーザーの名前を取得
					Integer userId = chatGroup.getUserId();
					Users user = userService.findById(userId);
					if (user != null) {
						recipientName = user.getLastName() + " " + user.getFirstName();
					}
				} else if (userDetails.getAuthorities().stream()
						.anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("USER"))) {
					// ログインユーザーがユーザーの場合、会社の名前を取得
					Integer companyId = chatGroup.getCompanyId();
					Companies company = companyService.findById(companyId);
					if (company != null) {
						recipientName = company.getCompanyName();
					}
				}
			}
			model.addAttribute("recipientName", recipientName);
		} else {
			model.addAttribute("chatMessages", List.of());
		}
		return "chat";
	}
}