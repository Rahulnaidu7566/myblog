package com.myblog2.Payload;

import com.myblog2.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> PostDto;
    private int pageNo;
    private int pageSize;
    private long TotalElement;
    private int Totalpages;
    private boolean last;

}
