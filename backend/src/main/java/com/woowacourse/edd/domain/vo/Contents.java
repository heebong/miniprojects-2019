package com.woowacourse.edd.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Contents {
    @Column
    @Lob
    private final String contents;

    public Contents(String contents) {
        this.contents = contents;
    }
}
