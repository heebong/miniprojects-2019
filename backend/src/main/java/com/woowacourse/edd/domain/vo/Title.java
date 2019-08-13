package com.woowacourse.edd.domain.vo;

import com.woowacourse.edd.exceptions.InvalidTitleException;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Title {
    private final String title;

    public Title(String title) {
        checkTitle(title);
        this.title = title;
    }

    private void checkTitle(String title) {
        if (Objects.isNull(title) || title.trim().isEmpty()) {
            throw new InvalidTitleException();
        }
    }
}
