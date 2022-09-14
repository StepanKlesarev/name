package com.example.name.Controllers;
import com.example.name.Models.Igryshki;
import com.example.name.Repository.IgryshkiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Igryshki")
public class IgryshkiController {
    @Autowired
    private IgryshkiRepository igryshkiRepository;

    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<Igryshki> igryshki = igryshkiRepository.findAll();
        model.addAttribute("igryshki", igryshki);
        return "Igryshki/Index";
    }


    @GetMapping("/add")
    public String add (
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("material") String material,
            @RequestParam("size") Integer size,
            @RequestParam("price") Integer price,
            Model model)
    {
        Igryshki igryshkiOne = new Igryshki(title, material, author, size, price);
        igryshkiRepository.save(igryshkiOne);
        return "redirect:/Igryshki/";
    }


    @GetMapping("/Search")
    public String GetAdd(
            @RequestParam("title") String title,
            Model model)
    {
        List<Igryshki> igryshkiList = igryshkiRepository.findByTitle(title);
        model.addAttribute("igryshki", igryshkiList);
        return "Igryshki/Index";
    }


    @GetMapping("/Searchs")
    public String GetAdds(
            @RequestParam("title") String title,
            Model model)
    {
        List<Igryshki> igryshkiList = igryshkiRepository.findByTitleContains(title);
        model.addAttribute("igryshki", igryshkiList);
        return "Igryshki/Index";
    }


    @GetMapping("/{id}")
    public String read(
            @PathVariable("id") Long id,
            Model model)
    {
        Optional<Igryshki> igryshki = igryshkiRepository.findById(id);
        ArrayList<Igryshki> igryshkiArrayList = new ArrayList<>();
        igryshki.ifPresent(igryshkiArrayList::add);
        model.addAttribute("igryshki", igryshkiArrayList);
        return "Igryshki/Info-Igryshki";
    }


    @GetMapping("/del/{id}")
    public String del(
            @PathVariable("id") Long id
    )
    {
        Igryshki igryshki = igryshkiRepository.findById(id).orElseThrow();
        igryshkiRepository.delete(igryshki);

//newsRepository.deleteById(id);
        return "redirect:/Igryshki/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            Model model
    )
    {
        if (!igryshkiRepository.existsById(id) )
        {
            return "redirect:/News/";
        }
        Optional<Igryshki> igryshki = igryshkiRepository.findById(id);
        ArrayList<Igryshki> igryshkiArrayList = new ArrayList<>();
        igryshki.ifPresent(igryshkiArrayList::add);
        model.addAttribute("Igryshki", igryshkiArrayList);
        model.addAttribute("title", igryshki.get().getTitle());
        model.addAttribute("author", igryshki.get().getAuthor());
        model.addAttribute("material", igryshki.get().getMaterial());
        model.addAttribute("size", igryshki.get().getSize());
        model.addAttribute("price", igryshki.get().getPrice());

        return "Igryshki/Edit-Igryshki";
    }


    @PostMapping("/edit/{id}")
    public String editNews(
            @PathVariable("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("material") String material,
            @RequestParam("size") Integer size,
            @RequestParam("price") Integer price,
            Model model
    )
    {
        if (!igryshkiRepository.existsById(id) )
        {
            return "redirect:/Igryshki/";
        }
        if ( title.isEmpty() || author.isEmpty() || material.isEmpty() || size.equals(null) || price.equals(null))
        {
            return "redirect:/Zoo/";
        }
        Igryshki igryshki = igryshkiRepository.findById(id).orElseThrow();


        igryshki.setTitle(title);
        igryshki.setAuthor(author);
        igryshki.setMaterial(material);
        igryshki.setSize(size);
        igryshki.setPrice(price);

        igryshkiRepository.save(igryshki);
        return "redirect:/Igryshki/";
    }
}
