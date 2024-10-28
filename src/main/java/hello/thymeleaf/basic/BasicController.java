package hello.thymeleaf.basic;


import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescape")
    public String textUnescape(Model model){
        model.addAttribute("data","<b>Spring!</b>");
        return "basic/text-unescape";
    }

    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("UserA", 10);
        User userB = new User("UserB", 15);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String,User> map = new HashMap<>();
        map.put("userA",userA);
        map.put("userB", userB);

        model.addAttribute("user",userA);
        model.addAttribute("users",list);
        model.addAttribute("userMap",map);
        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionData", "hello Session");
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class helloBean{
        public String hello(String data){
            return "hello" + data;
        }
    }

    @Data
    static class User {
        private String username;
        private int age;

        public User(String username,int age){
            this.age = age;
            this.username = username;
        }
    }
}
