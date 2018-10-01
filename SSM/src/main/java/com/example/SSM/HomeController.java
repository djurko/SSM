package com.example.SSM;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
	private final JobRepository repository;

	public HomeController(JobRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("/home");
		mav.addObject("jobsList", repository.findAll());
		return mav;
	}

	@RequestMapping(value = "/delete_job", method = RequestMethod.GET)
	public String handleDeleteJob(@RequestParam(name = "id") String id) {
		repository.deleteById(Long.parseLong(id));
		return "redirect:/home";
	}

	@RequestMapping(value = "/add_job", method = RequestMethod.POST)
	public String handleAddJob(@RequestBody Job tempjob) {
		Job job = new Job(tempjob.getMessageText(), tempjob.getTime());
		job.setChannel("sandbox");
		job.setStatus("Pending");
		repository.save(job);
		return "redirect:/home";
	}

}
