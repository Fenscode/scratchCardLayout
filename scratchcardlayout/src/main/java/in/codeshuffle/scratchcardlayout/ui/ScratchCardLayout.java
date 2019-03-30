package in.codeshuffle.scratchcardlayout.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import in.codeshuffle.scratchcardlayout.listener.ScratchListener;
import in.codeshuffle.scratchcardlayout.util.Utils;

public class ScratchCardLayout extends CardView implements ScratchCard.ScratchCardInterface {

    private ScratchCard scratchCard;
    private ScratchListener mListener;
    private Context mContext;

    public ScratchCardLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ScratchCardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ScratchCardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    /**
     * Set the scratch brush width
     *
     * @param mScratchWidth width in dp
     */
    public void setScratchWidth(float mScratchWidth) {
        scratchCard.setScratchWidth(Utils.dipToPx(mContext, mScratchWidth));
    }

    /**
     * Set the layout res of view to show for scratching
     *
     * @param mScratchDrawable layout res
     */
    public void setScratchDrawable(Drawable mScratchDrawable) {
        scratchCard.setScratchDrawable(mScratchDrawable);
    }

    /**
     * Scratch listener
     *
     * @param mListener listener object
     */
    public void setScratchListener(ScratchListener mListener) {
        this.mListener = mListener;
        scratchCard.setListener(mListener);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;
        scratchCard = new ScratchCard(context, attrs, defStyleAttr);
        scratchCard.setRevealListener(this);
        setupScratchView();
    }

    /**
     * Add the scratch card to layout
     */
    private void setupScratchView() {
        scratchCard.setLayoutParams(new CardView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        post(new Runnable() {
            @Override
            public void run() {
                addView(scratchCard);
            }
        });
    }

    /**
     * Stop scratch effect
     */
    public void stopScratching() {
        scratchCard.setVisibility(GONE);
    }

    public void setRevealFullAtPercent(int revealFullAtPercent) {
        scratchCard.setRevealFullAtPercent(revealFullAtPercent);
    }

    @Override
    public void onFullReveal() {
        stopScratching();
    }
}
