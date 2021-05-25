package com.bishe.back.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//定位图片或者文件地址
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //店铺图片
        registry.addResourceHandler("/img/shopPic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "shopPic" + System.getProperty("file.separator")
        );

        //默认图片
        registry.addResourceHandler("/img/defualt/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "defualt" + System.getProperty("file.separator")
        );

        //活动图片
        registry.addResourceHandler("/img/activePic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "activePic" + System.getProperty("file.separator")
        );

        //用户头像
        registry.addResourceHandler("/img/userPic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "userPic" + System.getProperty("file.separator")
        );

        //用户背景图
        registry.addResourceHandler("/img/userBackPic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "userBackPic" + System.getProperty("file.separator")
        );
        //稿件封面
        registry.addResourceHandler("/img/articlePic/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "articlePic" + System.getProperty("file.separator")
        );

        //视频地址
        registry.addResourceHandler("/video/**").addResourceLocations(
                "file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "video"
                        + System.getProperty("file.separator")
        );
    }
}
