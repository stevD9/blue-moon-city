package me.stef.bluemooncity.service.view;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorView implements ErrorController {

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            HttpStatus httpCode = HttpStatus.resolve(Integer.parseInt(status.toString()));

            if (httpCode == HttpStatus.NOT_FOUND)
                return "fourofour";
            if (httpCode == HttpStatus.FORBIDDEN)
                return "forbidden";
        }

        return "error";
    }
}
