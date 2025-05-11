package com.agony.picturebackend.api.imagesearch;

import com.agony.picturebackend.api.imagesearch.model.ImageSearchResult;
import com.agony.picturebackend.api.imagesearch.sub.GetImageFirstURLApi;
import com.agony.picturebackend.api.imagesearch.sub.GetImageListApi;
import com.agony.picturebackend.api.imagesearch.sub.GetImagePageURLApi;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: Agony
 * @create: 2025/5/10 20:22
 * @describe:
 */
@Slf4j
public class ImageSearchApiFacade {

    /**
     * 搜索图片
     *
     * @param imageUrl
     * @return
     */
    public static List<ImageSearchResult> searchImage(String imageUrl) {
        String imagePageUrl = GetImagePageURLApi.getImagePageUrl(imageUrl);
        String imageFirstUrl = GetImageFirstURLApi.getImageFirstUrl(imagePageUrl);
        List<ImageSearchResult> imageList = GetImageListApi.getImageList(imageFirstUrl);
        return imageList;
    }

    public static void main(String[] args) {
        // List<ImageSearchResult> imageList = searchImage("https://www.codefather.cn/logo.png");
        List<ImageSearchResult> imageList = searchImage("https://wx3.sinaimg.cn/mw690/007cCWrJgy1hte7hqaqj8j30m818ggnx.jpg");
        System.out.println("结果列表" + imageList);
    }
}
