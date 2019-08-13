package com.woowacourse.edd.domain.vo;

import javax.persistence.Embeddable;

@Embeddable
public class Contents {
    private final String contents;

    public Contents(String contents) {
        this.contents = contents;
    }
}
