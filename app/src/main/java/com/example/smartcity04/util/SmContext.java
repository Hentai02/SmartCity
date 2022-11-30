package com.example.smartcity04.util;

public interface SmContext {

    // 向其他activity传递信息 key
    String NEWS_EXTRA_TITLE = "news_extra_title";
    String NEWS_EXTRA_CONTENT = "news_extra_content";
    String NEWS_EXTRA_COVER = "news_extra_cover";
    String NEWS_EXTRA_TYPE = "news_extra_type";
    String NEWS_EXTRA_UPDATE_TIME = "news_extra_date_time";
    String NEWS_EXTRA_READ_NUM = "news_extra_read_num";
    String NEWS_EXTRA_ID = "news_extra_id";
    String NEW_EXTRA_COMMENT_NUM = "news_extra_commentNum";
    String NEWS_EXTRA_LIKE_NUM = "news_extra_likeNum";

    // 新闻分类共享首选项文件名称
    String SHARE_FILE_NEWS_CATEGORY = "news_category";
    // 新闻分类共享首选项key
    String PREFIX_CATEGORY_P = "category_p";

    // 共享首选项 用户Token 文件名
    String SHARE_FILE_USER_TOKEN = "smart_city_user_token";

    String SHARE_FILE_LIKE_NEWS  = "smart_city_like_news";


}
