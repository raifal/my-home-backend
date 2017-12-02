package de.rainerfaller.hsm.controller.ui;

import de.rainerfaller.hsm.dao.HomeAwayRepository;
import de.rainerfaller.hsm.dto.HomeAway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeAwayController {

    @Autowired
    private HomeAwayRepository homeAwayRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/hsm/home/setaway")
    public @ResponseBody
    String away() {
        save(false);
        return "processed";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hsm/home/sethome")
    public @ResponseBody
    String home() {
        save(true);
        return "processed";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/hsm/home/status")
    public @ResponseBody
    HomeAway homestatus() {
        return homeAwayRepository.findAll().iterator().next();
    }

    private void save(Boolean home) {
        homeAwayRepository.deleteAll();
        HomeAway homeAway = new HomeAway();
        homeAway.setHome(home);
        homeAwayRepository.save(homeAway);
    }
}
