package org.wmg.security.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Menu {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String title;
    private String size;
    private String time;
    private String path;
    private String tag;
    private Long fsize;
}
