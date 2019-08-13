package com.woowacourse.edd.domain.vo;

import com.woowacourse.edd.exceptions.InvalidYoutubeIdException;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class YoutubeId {
    private final String youtubeId;

    public YoutubeId(String youtubeId) {
        checkYoutubeId(youtubeId);
        this.youtubeId = youtubeId;
    }

    private void checkYoutubeId(String youtubeId) {
        if (Objects.isNull(youtubeId) || youtubeId.trim().isEmpty()) {
            throw new InvalidYoutubeIdException();
        }
    }
}
