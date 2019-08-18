package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.EddApplicationTests;
import com.woowacourse.edd.application.dto.VideoSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VideoControllerTests extends EddApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    private final String DEFAULT_VIDEO_YOUTUBEID = "S8e1geEpnTA";
    private final String DEFAULT_VIDEO_TITLE = "제목";
    private final String DEFAULT_VIDEO_CONTENTS = "내용";
    private final String VIDEOS_URI = "/v1/videos";

    @Test
    void find_video_by_id() {
        save();
        findVideo("/1").isOk()
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.youtubeId").isEqualTo(DEFAULT_VIDEO_YOUTUBEID)
            .jsonPath("$.title").isEqualTo(DEFAULT_VIDEO_TITLE)
            .jsonPath("$.contents").isEqualTo(DEFAULT_VIDEO_CONTENTS)
            .jsonPath("$.createDate").isEqualTo(getFormedDate());
    }

    @Test
    void find_video_by_id_not_found() {
        executeFail(findVideo("/100"), "그런 비디오는 존재하지 않아!");
    }

    @Test
    void find_videos_by_date() {
        findVideos("date").isOk();
    }

    @Test
    void find_videos_by_views() {
        executeFail(findVideos("view"), "지원되지 않는 필터입니다");
    }

    @Test
    void save() {
        VideoSaveRequestDto videoSaveRequestDto = new VideoSaveRequestDto(DEFAULT_VIDEO_YOUTUBEID, DEFAULT_VIDEO_TITLE, DEFAULT_VIDEO_CONTENTS);

        saveVideo(videoSaveRequestDto).isOk()
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.youtubeId").isEqualTo(DEFAULT_VIDEO_YOUTUBEID)
            .jsonPath("$.title").isEqualTo(DEFAULT_VIDEO_TITLE)
            .jsonPath("$.contents").isEqualTo(DEFAULT_VIDEO_CONTENTS)
            .jsonPath("$.createDate").isEqualTo(getFormedDate());
    }

    @Test
    void save_invalid_youtube_id() {
        String youtubeId = null;

        VideoSaveRequestDto videoSaveRequestDto = new VideoSaveRequestDto(youtubeId, DEFAULT_VIDEO_TITLE, DEFAULT_VIDEO_CONTENTS);

        executeFail(saveVideo(videoSaveRequestDto), "유투브 아이디는 필수로 입력해야합니다.");
    }

    @Test
    void save_empty_title() {
        String title = "";

        VideoSaveRequestDto videoSaveRequestDto = new VideoSaveRequestDto(DEFAULT_VIDEO_YOUTUBEID, title, DEFAULT_VIDEO_CONTENTS);

        executeFail(saveVideo(videoSaveRequestDto), "제목은 한 글자 이상이어야합니다");

    }

    @Test
    void save_invalid_contents() {
        String contents = " ";

        VideoSaveRequestDto videoSaveRequestDto = new VideoSaveRequestDto(DEFAULT_VIDEO_YOUTUBEID, DEFAULT_VIDEO_TITLE, contents);

        executeFail(saveVideo(videoSaveRequestDto), "내용은 한 글자 이상이어야합니다");
    }

    private StatusAssertions findVideo(String uri) {
        return executeGet(VIDEOS_URI + uri)
            .exchange()
            .expectStatus()
            ;
    }

    private StatusAssertions findVideos(String filter) {
        return executeGet(VIDEOS_URI + "?filter=" + filter + "&page=0&limit=5")
                .exchange()
                .expectStatus()
                ;
    }

    private StatusAssertions saveVideo(VideoSaveRequestDto videoSaveRequestDto) {
        return executePost(VIDEOS_URI)
            .body(Mono.just(videoSaveRequestDto), VideoSaveRequestDto.class)
            .exchange()
            .expectStatus();
    }

    private void executeFail(StatusAssertions statusAssertions, String errorMessage) {
        WebTestClient.BodyContentSpec bodyContentSpec = statusAssertions
            .isBadRequest()
            .expectBody();

        checkErrorResponse(bodyContentSpec, errorMessage);
    }

    private void checkErrorResponse(WebTestClient.BodyContentSpec bodyContentSpec, String errorMessage) {
        bodyContentSpec.jsonPath("$.result").isEqualTo("FAIL")
            .jsonPath("$.message").isEqualTo(errorMessage);
    }

    private WebTestClient.RequestHeadersSpec<?> executeGet(String uri) {
        return webTestClient.get().uri(uri);
    }

    private WebTestClient.RequestBodySpec executePost(String uri) {
        return webTestClient.post().uri(uri);
    }

    private String getFormedDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
        return now.format(formatter);
    }
}
