package com.woowacourse.edd.application.service;

import com.woowacourse.edd.application.converter.VideoConverter;
import com.woowacourse.edd.domain.Video;
import com.woowacourse.edd.domain.VideoRepository;
import com.woowacourse.edd.presentation.controller.VideoResponse;
import com.woowacourse.edd.presentation.dto.VideoSaveRequestDto;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    private VideoRepository videoRepository;
    private VideoConverter videoConverter;

    public VideoService(VideoRepository videoRepository, VideoConverter videoConverter) {
        this.videoRepository = videoRepository;
        this.videoConverter = videoConverter;
    }

    public VideoResponse save(VideoSaveRequestDto requestDto) {
        Video video = save(videoConverter.toEntity(requestDto));

        return videoConverter.toResponse(video);
    }

    private Video save(Video video) {
        return videoRepository.save(video);
    }
}
