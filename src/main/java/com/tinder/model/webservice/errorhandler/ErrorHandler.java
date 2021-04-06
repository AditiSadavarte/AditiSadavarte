package com.tinder.model.webservice.errorhandler;

import java.io.IOException;
import java.util.logging.Logger;


import com.tinder.TinderWebappApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class ErrorHandler implements ResponseErrorHandler {
//    private static final Logger LOGGER = Logger.getLogger(ErrorHandler.class.getName());
//    private static final Logger LOGGER = Sys;
public static final java.util.logging.Logger LOGGER = Logger.getLogger(ErrorHandler.class.getSimpleName());

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtil.isError(response.getStatusCode());
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
            LOGGER.info("User no longer exists");
        }
    }

    public static class RestUtil {
        private RestUtil() {}
        
        public static boolean isError(HttpStatus status) {
            HttpStatus.Series series = status.series();
            return HttpStatus.Series.CLIENT_ERROR.equals(series) || HttpStatus.Series.SERVER_ERROR.equals(series);
        }
    }
}