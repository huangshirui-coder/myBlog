package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_web_info")
public class WebInfo extends Model {
    /**
     * 主键id
     */
    @TableId
    private Integer id;

    /**
     * 网站名称
     */
    private String webName;

    /**
     * 网站欢迎词
     */
    private String webWelcome;

    /**
     * 网站注意事项
     */
    private String webNotices;

    /**
     * 网站封面图
     */
    private String cover;

    /**
     * 网站标题
     */
    private String webTitle;

    /**
     * 网站头像
     */
    private String webHead;

    /**
     * 网站页脚
     */
    private String webFootor;

    private String loginCover;

    private String adminLoginCover;
}
