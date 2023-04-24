package com.samir.tickettrackerapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.samir.tickettrackerapplication.model.Ticket;
import com.samir.tickettrackerapplication.service.TicketService;

@Controller
@RequestMapping("ticket")
public class TicketController {
	TicketService ticketService;
	
	public TicketController(TicketService tecketService) {
		super();
		this.ticketService = tecketService;
	}

	@GetMapping("/home")
	public String showTicket(Model model) {
		model.addAttribute("tickets",ticketService.getAllTickets() );
		return "ticket-home";
	}
	@GetMapping("/create-ticket")
	public String createTicket(Model model) {
		model.addAttribute("tickets",new Ticket() );
		return "tickets";
	}
	@PostMapping("/saveTicket")
	public String saveTicket(Model model, @ModelAttribute("ticket") Ticket ticket) {
		ticketService.saveTicket(ticket);
		return "redirect:/ticket/home";
	}
	@GetMapping("/delete/{id}")
	public String deleteTicket(Model model, @PathVariable int id) {
		ticketService.deleteTicketById(id);
		return "redirect:/ticket/home";
	}
	@GetMapping("/edit/{id}")
	public String editTicket(Model model, @PathVariable int id) {
		model.addAttribute("ticket", ticketService.getTicketById(id));
		return "update";
	}
	@PostMapping("/update/{id}")
	public String updateTicket(Model model, @PathVariable int id, @ModelAttribute("ticket") Ticket ticket) {
		Ticket existingTicket =ticketService.getTicketById(id);
		existingTicket.setId(id);
		existingTicket.setTicket_title(ticket.getTicket_title());
		existingTicket.setTicket_short_description(ticket.getTicket_short_description());
		existingTicket.setTicket_created_on(ticket.getTicket_created_on());
		ticketService.updateTicket(existingTicket);
		return "redirect:/ticket/home";
	}
}
