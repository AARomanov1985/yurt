package org.romanov.yurt.analysis.facade.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.romanov.yurt.analysis.facade.FileAnalyzerFacade;
import org.romanov.yurt.analysis.model.AnalysisModel;
import org.romanov.yurt.analysis.model.State;
import org.romanov.yurt.analysis.service.AnalysisService;
import org.romanov.yurt.analysis.strategy.AnalysisStrategy;
import org.romanov.yurt.post.data.PostData;
import org.romanov.yurt.post.facade.PostFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

import static java.util.Objects.nonNull;

@Component
public class DefaultFileAnalyzerFacade implements FileAnalyzerFacade {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultFileAnalyzerFacade.class);

    @Resource
    private AnalysisService analysisService;
    @Resource
    private AnalysisStrategy analyzerStrategy;
    @Resource
    private PostFacade postFacade;

    @Override
    public long analyzeFile(final URL url) {

        var analysisModel = analysisService.createAnalysisModel();
        var thread = new Thread(() -> {
            LOG.info("Starting analysis for {}", url.getPath());

            try (var byteChannel = Channels.newChannel(url.openStream())) {
                performAnalysis(byteChannel, analysisModel);
            } catch (IOException ex) {
                handleError(ex.getMessage(), analysisModel);
            }

            LOG.info("Finished analysis for {}", url.getPath());
        });
        thread.start();

        return analysisModel.getUid();
    }

    protected void performAnalysis(final ReadableByteChannel byteChannel, final AnalysisModel analysisModel)
            throws JsonProcessingException {
        var scanner = new Scanner(byteChannel);
        while (scanner.hasNextLine() || isIoException(scanner)) {
            var xml = scanner.nextLine();
            if (postFacade.isPost(xml)) {
                var postData = postFacade.getPostData(xml);
                analyzePost(postData, analysisModel);
            }
        }
        finishAnalysis(scanner, analysisModel);
    }

    protected void analyzePost(final PostData postData, final AnalysisModel analysisModel) {
        postData.setAnalysisUid(analysisModel.getUid());
        analyzerStrategy.fillDetailsFromPost(postData, analysisModel);
        postFacade.savePostFromPostData(postData);
    }

    private boolean isIoException(final Scanner scanner) {
        return nonNull(scanner.ioException());
    }

    protected void finishAnalysis(final Scanner scanner, final AnalysisModel analysisModel) {
        if (isIoException(scanner)) {
            handleError(scanner.ioException().getMessage(), analysisModel);
        } else {
            analysisModel.setState(State.FINISHED);
            analyzerStrategy.calculateAvgScore(analysisModel);
            analyzerStrategy.calculateAnalyseTime(analysisModel);
            analysisService.saveAnalysisModel(analysisModel);
        }
        scanner.close();
    }

    private void handleError(String errorMessage, final AnalysisModel analysisModel) {
        LOG.error("An exception {} occurred", errorMessage);
        analysisModel.setState(State.FAILED);
        analysisModel.setFailedSummary(errorMessage);
        analysisService.saveAnalysisModel(analysisModel);
    }
}
