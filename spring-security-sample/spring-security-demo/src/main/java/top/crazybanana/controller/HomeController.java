package top.crazybanana.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Bob
 * @Datetime: 2018-12-03-0:55
 */
@RequestMapping("/home")
@RestController
public class HomeController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
