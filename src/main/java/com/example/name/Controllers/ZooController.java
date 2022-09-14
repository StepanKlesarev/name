package com.example.name.Controllers;

import com.example.name.Models.Zoo;
import com.example.name.Repository.ZooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/Zoo")
public class ZooController {
    @Autowired
    private ZooRepository zooRepository;

    @GetMapping("/")
    public String Index(Model model)
    {
        Iterable<Zoo> zoo = zooRepository.findAll();
        model.addAttribute("zoo", zoo);
        return "Zoo/Index";
    }


    @GetMapping("/add")
    public String add (
   @RequestParam("title") String title,
   @RequestParam("author") String author,
   @RequestParam("name") String name,
   @RequestParam("world") Integer world,
   @RequestParam("live") Integer live,
   Model model)
    {
        Zoo zooOne = new Zoo(title, name, author, world, live);
        zooRepository.save(zooOne);
        return "redirect:/Zoo/";
    }


    @GetMapping("/Search")
    public String GetAdd(
@RequestParam("title") String title,
    Model model)
    {
        List<Zoo> zooList = zooRepository.findByTitle(title);
        model.addAttribute("zoo", zooList);
        return "Zoo/Index";
    }


    @GetMapping("/Searchs")
    public String GetAdds(
@RequestParam("title") String title,
    Model model)
    {
        List<Zoo> zooList = zooRepository.findByTitleContains(title);
        model.addAttribute("zoo", zooList);
        return "Zoo/Index";
    }


    @GetMapping("/{id}")
    public String read(
@PathVariable("id") Long id,
    Model model)
    {
        Optional<Zoo> news = zooRepository.findById(id);
        ArrayList<Zoo> zooArrayList = new ArrayList<>();
        news.ifPresent(zooArrayList::add);
        model.addAttribute("zoo", zooArrayList);
        return "Zoo/Info-Zoo";
    }


    @GetMapping("/del/{id}")
    public String del(
@PathVariable("id") Long id
)
    {
        Zoo zoo = zooRepository.findById(id).orElseThrow();
        zooRepository.delete(zoo);

//newsRepository.deleteById(id);
        return "redirect:/Zoo/";
    }


    @GetMapping("/edit/{id}")
    public String edit(
@PathVariable("id") Long id,
    Model model
)
    {
        if (!zooRepository.existsById(id) )
        {
            return "redirect:/Zoo/";
        }
        Optional<Zoo> zoo = zooRepository.findById(id);
        ArrayList<Zoo> zooArrayList = new ArrayList<>();
        zoo.ifPresent(zooArrayList::add);
        model.addAttribute("title", zoo.get().getTitle());
        model.addAttribute("author", zoo.get().getAuthor());
        model.addAttribute("name", zoo.get().getName());
        model.addAttribute("world", zoo.get().getWorld());
        model.addAttribute("live", zoo.get().getLive());

        return "Zoo/Edit-Zoo";
    }


    @PostMapping("/edit/{id}")
    public String editZoo(
@PathVariable("id") Long id,
@RequestParam("title") String title,
@RequestParam("author") String author,
@RequestParam("name") String name,
@RequestParam("world") Integer world,
@RequestParam("live") Integer live,
    Model model
)
    {
        if (!zooRepository.existsById(id) )
        {
            return "redirect:/Zoo/";
        }
        if ( title.isEmpty() || author.isEmpty() || name.isEmpty() || world.equals(null) || live.equals(null))
        {
            return "redirect:/Zoo/";
        }
        Zoo zoo = zooRepository.findById(id).orElseThrow();


        zoo.setTitle(title);
        zoo.setAuthor(author);
        zoo.setName(name);
        zoo.setWorld(world);
        zoo.setLive(live);

        zooRepository.save(zoo);
        return "redirect:/Zoo/";
    }
}
