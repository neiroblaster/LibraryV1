package com.shchayuk.mvcProject1.config.controllers;

import com.shchayuk.mvcProject1.config.dao.BookDAO;
import com.shchayuk.mvcProject1.config.dao.PersonDAO;
import com.shchayuk.mvcProject1.config.models.Book;
import com.shchayuk.mvcProject1.config.models.Person;
import com.sun.xml.internal.ws.policy.sourcemodel.PolicySourceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final PersonDAO personDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    public PersonController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model,
                       @ModelAttribute ("book") Book book){
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", bookDAO.showLentBooks(id));
        return "people/show";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute ("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }


    @GetMapping("{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personDAO.update(person, id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }


}
