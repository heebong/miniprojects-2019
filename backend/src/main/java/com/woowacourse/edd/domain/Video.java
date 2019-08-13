package com.woowacourse.edd.domain;

import com.woowacourse.edd.domain.vo.Contents;
import com.woowacourse.edd.domain.vo.Title;
import com.woowacourse.edd.domain.vo.YoutubeId;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @Column(nullable = false)
    private YoutubeId youtubeId;
    @Embedded
    @Column(nullable = false)
    private Title title;
    @Embedded
    @Column
    private Contents contents;
    @CreationTimestamp
    private LocalDateTime createDate;

    private Video() {

    }

    public Video(YoutubeId youtubeId, Title title, Contents contents) {
        this.youtubeId = youtubeId;
        this.title = title;
        this.contents = contents;
    }
}
