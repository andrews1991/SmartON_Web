package com.protean.student.StudentPortal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.protean.student.StudentPortal.model.EventDetails;
import com.protean.student.StudentPortal.service.EventDetailsService;


@RestController
//@RequestMapping("/api/events")
public class EventDetailsController {
	
	@Autowired(required=true)
	EventDetailsService eventDetailsService;
	
	 @PostMapping(value="/add",headers="Accept=application/json")
	//@PostMapping(value="/add")
	public EventDetails addEvent(@Valid @RequestBody EventDetails eventdetails) {
		return eventDetailsService.addEvent(eventdetails);
	}
	
	@GetMapping(value="/get/{id}")
	public EventDetails getStudentById(@PathVariable Integer id) {
		return eventDetailsService.getEventById(id);
	}
	
	@GetMapping(value="/getall") 
	public List<EventDetails> getAllStudents() {
		return eventDetailsService.getAllEvents();
	}
	
	@PutMapping(value="/update/{id}")
	public EventDetails updateStudent(@RequestBody EventDetails eventDetails) {
		return eventDetailsService.updateEventDetails(eventDetails);
	}

	/*
	 * @PostMapping("/fileupload") public String fileUpload(@RequestParam("name")
	 * String name, @RequestParam("file") MultipartFile file) { try {
	 * //logger.info("Name= " + name); byte[] image = file.getBytes(); EventDetails
	 * model = new EventDetails(name, image); int saveImage =
	 * myService.saveImage(model); if (saveImage == 1) { return "success"; } else {
	 * return "error"; } } catch (Exception e) { //logger.error("ERROR", e); return
	 * "error"; } }
	 * 
	 * @GetMapping("/getDetail/{id}") public String getDbDetils(@PathVariable String
	 * id, Model model) { try { logger.info("Id= " + id); MyModel imagesObj =
	 * myService.getImages(Long.parseLong(id)); model.addAttribute("id",
	 * imagesObj.getId()); model.addAttribute("name", imagesObj.getName()); byte[]
	 * encode = java.util.Base64.getEncoder().encode(imagesObj.getImage());
	 * model.addAttribute("image", new String(encode, "UTF-8")); return
	 * "imagedetails"; } catch (Exception e) { logger.error("Error", e);
	 * model.addAttribute("message", "Error in getting image"); return "redirect:/";
	 * } } }
	 */
}
