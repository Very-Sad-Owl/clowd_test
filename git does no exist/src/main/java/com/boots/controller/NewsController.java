package com.boots.controller;

import com.boots.entity.DayParts;
import com.boots.service.NewsService;
import com.boots.service.util.DayPartDefiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsController {

    private static final Logger logger = LogManager.getLogger(NewsController.class);

    @Autowired
    NewsService newsService;

    @Autowired
    DayPartDefiner dayPartDefiner;

    @GetMapping("/news")
    public String loadNews(Model model) {
        model.addAttribute("allNews", newsService.getArticles());
        logger.info(dayPartDefiner.getCurrentDayPart().toString());
        model.addAttribute("dayPart", dayPartDefiner.getCurrentDayPart().toString());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        model.addAttribute("username", username);
        return "/news";
    }

    @GetMapping("/news/{articleId}")
    public String readArticle(@PathVariable("articleId") Long id, Model model) {
        model.addAttribute("currentArticle", newsService.getArticle(id));
        return "/news";
    }

    @PostMapping("/news/{articleId}")
    public String rateArticle(@RequestParam(required = true, defaultValue = "" ) Long article,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        boolean isLiked = action.equals("like");
        newsService.rateArticle(article, isLiked);
        model.addAttribute("currentArticle", newsService.getArticle(article));
        return "/news";
    }
}
