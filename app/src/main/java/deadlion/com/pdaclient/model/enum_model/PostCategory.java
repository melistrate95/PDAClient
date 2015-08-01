package deadlion.com.pdaclient.model.enum_model;

/**
 * Created by Mike on 27.07.2015.
 */
public enum PostCategory {
    NEWS_CATEGORY,
    ARTICLE_CATEGORY,
    REVIEW_CATEGORY,
    PROGRAM_CATEGORY,
    GAME_CATEGORY,
    FAVORITE_CATEGORY;

    public static PostCategory getCategoryByString(String category) {
        switch(category) {
            case "NEWS_CATEGORY":
                return NEWS_CATEGORY;
            case "ARTICLE_CATEGORY":
                return ARTICLE_CATEGORY;
            case "REVIEW_CATEGORY":
                return REVIEW_CATEGORY;
            case "PROGRAM_CATEGORY":
                return PROGRAM_CATEGORY;
            case "GAME_CATEGORY":
                return GAME_CATEGORY;
            case "FAVORITE_CATEGORY":
                return FAVORITE_CATEGORY;
            default:
                return null;
        }
    }
}
