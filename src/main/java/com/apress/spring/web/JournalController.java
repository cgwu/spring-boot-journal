package com.apress.spring.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apress.spring.domain.Journal;
import com.apress.spring.rabbitmq.Producer;
import com.apress.spring.repository.JournalRepository;

@Controller
public class JournalController {
	
	@Autowired
	JournalRepository repo;

	@RequestMapping("/")
	public String home()
	{
		return "redirect:/login";
	}
	
	@RequestMapping("/login")
	public String login(Model model, HttpSession session) {
//		org.hibernate.dialect.PostgreSQLDialect;
		model.addAttribute("journal", repo.findAll());
		session.setAttribute("now", new Date());
		return "login";
	}
	
	@RequestMapping("/index")
	public String index(Model model, HttpSession session) {
//		org.hibernate.dialect.PostgreSQLDialect;
		model.addAttribute("journal", repo.findAll());
		session.setAttribute("now", new Date());
		return "index";
//		return "redirect:/url";
	}
	
	@RequestMapping("/url")
	public String url(Model model, HttpSession session) {
		return "url";
	}
	
	@RequestMapping(value="/journal", produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
	public @ResponseBody List<Journal> getJournal() {
		return repo.findAll();
	}

//	@RequestMapping(value="/journal-xml", produces={MediaType.APPLICATION_XML_VALUE})
//	public @ResponseBody List<Journal> getJournalXml() {
//		return repo.findAll();
//	}
	
	@Value("${myqueue}")
	String queue;

	@Autowired
	Producer producer;
	
	@RequestMapping("/msg")
	@ResponseBody
	public String sendMessage( @RequestParam(value = "msg") String msg) {
		producer.sendTo(queue, "消息封装[" + msg + "]");
		return "发送消息成功";
	}
	
}
