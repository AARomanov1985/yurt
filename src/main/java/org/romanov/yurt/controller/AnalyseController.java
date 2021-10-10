package org.romanov.yurt.controller;

import org.romanov.yurt.analysis.dto.AnalysisDto;
import org.romanov.yurt.analysis.dto.ResultDto;
import org.romanov.yurt.analysis.facade.AnalysisFacade;
import org.romanov.yurt.analysis.facade.FileAnalyzerFacade;
import org.romanov.yurt.controller.dto.AnalyseRequest;
import org.romanov.yurt.controller.dto.AnalyseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
    @Resource
    private AnalysisFacade analysisFacade;

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

    @GetMapping(value="{uid}")
    @ResponseBody
    public AnalysisDto getAnalyseForId(@PathVariable("uid") long uid) {
        return analysisFacade.getAnalysisDtoForUid(uid);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<AnalysisDto> getAll() {
        return analysisFacade.getAllAnalysis();
    }

    @DeleteMapping(value="{uid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public ResultDto deleteById(@PathVariable("uid") long uid) {
        return analysisFacade.deleteByUid(uid);
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
