package org.romanov.yurt.controller;

import org.romanov.yurt.analysis.facade.FileAnalyzerFacade;
import org.romanov.yurt.controller.dto.AnalyseRequest;
import org.romanov.yurt.controller.dto.AnalyseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/analyse")
public class AnalyseController {

    public static final Pattern pattern = Pattern.compile("((http|https)://)(www.)?"
            + "[a-zA-Z0-9@:%._+?&/=]"
            + "{2,256}\\.[a-z]"
            + "{2,6}\\b([-a-zA-Z0-9@:%"
            + "._+?&/=]*)");
    private static final Logger LOG = LoggerFactory.getLogger(AnalyseController.class);

    @Resource
    private FileAnalyzerFacade fileAnalyzerFacade;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public AnalyseResponse analyse(@RequestBody AnalyseRequest url) {
        checkURL(url.getUrl());
        try {
            long id = fileAnalyzerFacade.analyzeFile(new URL(url.getUrl()));
            AnalyseResponse response = new AnalyseResponse();
            response.setId(id);
            LOG.info("returned id {}", id);
            return response;
        } catch (MalformedURLException e) {
            LOG.error(e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public void getAnalyseForId(@RequestParam long analyseId) {
        // TODO
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping("/all")
    public void getAll() {
        // TODO
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteForId(@RequestParam long analyseId) {
        // TODO
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    protected void checkURL(String url) {
        if (isInvalidURL(url)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid url " + url);
        }
    }

    private boolean isInvalidURL(String url) {
        return isNull(url) || !pattern.matcher(url).matches();
    }
}
