package deadlion.com.pdaclient.model.enum_model;

public enum PostCategory {
    NEWS_CATEGORY,
    ARTICLE_CATEGORY,
    REVIEW_CATEGORY,
    PROGRAM_CATEGORY,
    GAME_CATEGORY,
    FAVORITE_CATEGORY;

    public static PostCategory getPostCategory(int spinnerCategory) {
        PostCategory postCategory = null;
        switch (spinnerCategory) {
            case SpinnerCategory.NEWS_CATEGORY:
                postCategory = PostCategory.NEWS_CATEGORY;
                break;
            case SpinnerCategory.ARTICLE_CATEGORY:
                postCategory = PostCategory.ARTICLE_CATEGORY;
                break;
            case SpinnerCategory.REVIEW_CATEGORY:
                postCategory = PostCategory.REVIEW_CATEGORY;
                break;
            case SpinnerCategory.PROGRAM_CATEGORY:
                postCategory = PostCategory.PROGRAM_CATEGORY;
                break;
            case SpinnerCategory.GAME_CATEGORY:
                postCategory = PostCategory.GAME_CATEGORY;
                break;
            case SpinnerCategory.FAVORITE_CATEGORY:
                postCategory = PostCategory.FAVORITE_CATEGORY;
                break;
        }
        return postCategory;
    }
}
