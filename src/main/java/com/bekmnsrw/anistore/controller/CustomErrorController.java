package com.bekmnsrw.anistore.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping ("/error")
    private ModelAndView renderErrorPage(HttpServletRequest httpServletRequest) {
        ModelAndView errorPage = new ModelAndView("error");
        String errorMessage = "";
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            errorPage.addObject("statusCode", statusCode);

            switch (statusCode) {
                case 403 -> errorMessage = "Forbidden";
                case 404 -> errorMessage = "Page not found";
            }
        }

        errorPage.addObject("errorMessage", errorMessage);

        return errorPage;
    }
}
