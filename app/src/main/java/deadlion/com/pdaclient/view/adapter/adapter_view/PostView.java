package deadlion.com.pdaclient.view.adapter.adapter_view;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import deadlion.com.pdaclient.R;
import deadlion.com.pdaclient.model.Post;
import deadlion.com.pdaclient.view.listener.OnPostOverflowSelectedListener;

/**
 * Created by Михаил on 05.08.2015.
 */
public class PostView {

    private Context context;

    private View currentRootView;
    private Post currentPost;

    private View overflowButton;
    private TextView titleTextView;
    private ImageView photoImageView;
    private TextView dataTextView;
    private TextView dateOfPublicationView;
    private TextView authorView;
    private TextView countCommentView;

    public PostView(Context context) {
        this.context = context;
    }

    public void setCurrentView(View currentRootView, Post currentPost) {
        this.currentRootView = currentRootView;
        this.currentPost = currentPost;
    }

    private View buildOverflowButton() {
        overflowButton = currentRootView.findViewById(R.id.post_overflow);
        overflowButton.setOnClickListener(new OnPostOverflowSelectedListener(context, currentPost));
        return overflowButton;
    }

    private TextView buildTitleTextView() {
        titleTextView = (TextView) currentRootView.findViewById(R.id.news_title);
        titleTextView.setText(currentPost.getTitle());
        return titleTextView;
    }

    private ImageView buildPhotoImageView() {
        photoImageView = (ImageView)currentRootView.findViewById(R.id.news_photo);
        Picasso.with(context)
                .load(currentPost.getPhotoUrl())
                .into(photoImageView);
        return photoImageView;
    }

    private TextView buildDataTextView() {
        dataTextView = (TextView) currentRootView.findViewById(R.id.news_data);
        dataTextView.setText(currentPost.getShortPostText());
        return dataTextView;
    }

    private TextView buildDateOfPublicationView() {
        dateOfPublicationView = (TextView) currentRootView.findViewById(R.id.date_of_publication);
        dateOfPublicationView.setText(currentPost.getDataOfPublication());
        return dateOfPublicationView;
    }

    private TextView buildAuthorView() {
        authorView = (TextView) currentRootView.findViewById(R.id.name_author);
        authorView.setText(currentPost.getAuthor().getUserLogin());
        return authorView;
    }

    private TextView buildCountCommentView() {
        countCommentView = (TextView) currentRootView.findViewById(R.id.count_comment);
        countCommentView.setText(currentPost.getCountOfComments() + "");
        return countCommentView;
    }

    /*****************Get Final Adapter View*************/

    public void buildFullPostItem() {
        buildOverflowButton();
        buildTitleTextView();
        buildPhotoImageView();
        buildDataTextView();
        buildDateOfPublicationView();
        buildAuthorView();
        buildCountCommentView();
    }

    public void buildFullWithoutImagePostItem() {
        buildOverflowButton();
        buildTitleTextView();
        buildDataTextView();
        buildDateOfPublicationView();
        buildAuthorView();
        buildCountCommentView();
    }

    public void buildShortPostItem() {
        buildOverflowButton();
        buildTitleTextView();
        buildPhotoImageView();
        buildDateOfPublicationView();
        buildAuthorView();
        buildCountCommentView();
    }

    public void buildShortWithoutImagePostItem() {
        buildOverflowButton();
        buildTitleTextView();
        buildDateOfPublicationView();
        buildAuthorView();
        buildCountCommentView();
    }
}
