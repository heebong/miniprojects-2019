package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.service.VideoService;
import com.woowacourse.edd.presentation.dto.VideoSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {
    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/videos")
    public VideoResponse saveVideo(@RequestBody VideoSaveRequestDto requestDto) {
        VideoResponse response = videoService.save(requestDto);
        return response;
    }
}
