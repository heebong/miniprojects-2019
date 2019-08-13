package com.woowacourse.edd.domain.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Contents {
    @Column
    @Lob
    private String contents;

    private Contents() {
    }

    public Contents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }
}
