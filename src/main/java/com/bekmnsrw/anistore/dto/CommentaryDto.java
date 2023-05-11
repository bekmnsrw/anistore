package com.bekmnsrw.anistore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentaryDto {
    private Long id;
    private String content;
    private String createdAt;
    private String authorName;
}
