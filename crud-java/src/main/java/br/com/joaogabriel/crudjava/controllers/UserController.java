package br.com.joaogabriel.crudjava.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import br.com.joaogabriel.crudjava.models.User;
import br.com.joaogabriel.crudjava.repositories.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ModelAndView list() {
        // ModelAndView modelAndView = new ModelAndView("user/list");

        // modelAndView.addObject("users", userRepository.findAll());

        // return modelAndView;

        return new ModelAndView("user/list", Map.of("users", userRepository.findAll()));
    }

    @GetMapping("/create")
    public ModelAndView create() {
        // ModelAndView modelAndView = new ModelAndView("user/form");

        // modelAndView.addObject("user", new User());

        // return modelAndView;

        return new ModelAndView("user/form", Map.of("user", new User()));
    }

    @PostMapping("/create")
    public String create(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/form";
        }

        userRepository.save(user);

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable long id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // ModelAndView modelAndView = new ModelAndView("user/form");

        // modelAndView.addObject("user", user.get());

        // return modelAndView;

        return new ModelAndView("user/form", Map.of("user", user.get()));
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/form";
        }

        userRepository.save(user);

        return "redirect:/";
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable long id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ModelAndView("user/remove", Map.of("user", user.get()));
    }

    @PostMapping("/remove/{id}")
    public String remove(User user) {
        userRepository.delete(user);

        return "redirect:/";
    }
}
