package de.rainerfaller.hsm.controller;

import de.rainerfaller.hsm.service.MpiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Controller
@RequestMapping("/hsm/mpi")
public class MpiController {
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    String sayHello(HttpServletRequest request) {
        List<String> headers = new ArrayList<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            Enumeration<String> values = request.getHeaders(key);
            while (values.hasMoreElements()) {
                String value = values.nextElement();
                headers.add(key + ":" + value);
            }
        }

        new MpiService().processRawTemperatureData(headers);

        return "Processed";
    }
}