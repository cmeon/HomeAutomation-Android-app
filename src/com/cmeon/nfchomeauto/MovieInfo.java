package com.cmeon.nfchomeauto;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MovieInfo extends Activity {

    private boolean mShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.video_fragment);
	
	if (savedInstanceState == null) {
	    getFragmentManager()
		.beginTransaction()
		.add(R.id.movie_info_container, new FrontFragment())
		.commit();
	} else {
            mShowingBack = (getFragmentManager().getBackStackEntryCount() > 0);
        }
    }

    /**
     * A fragment representing the front of the card.
     */
    public class FrontFragment extends Fragment {
	public FrontFragment() {
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
				 Bundle savedInstanceState) {
	    View v = inflater.inflate(R.layout.movie_poster, container, false);
            assert v != null;
            v.setClickable(true);
	    v.setOnClickListener( new View.OnClickListener() {
		    @Override
		    public void onClick(View view) {
			flipCard();
		    }
		});
	    return v;
        }
    }
    
    /**
     * A fragment representing the back of the card.
     */
    public class BackFragment extends Fragment {
	public BackFragment() {
	}
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
				 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.movie_info, container, false);
            assert v != null;
            v.setClickable(true);
	    v.setOnClickListener( new View.OnClickListener() {
		    @Override
		    public void onClick(View view) {
			flipCard();
		    }
		});
	    return v;
        }
    }


    private void flipCard() {
	if (mShowingBack) {
	    mShowingBack = false;
	    getFragmentManager().popBackStack();
	    return;
	}

	// Flip to the back.

	mShowingBack = true;

	// Create and commit a new fragment transaction that adds the fragment for the back of
	// the card, uses custom animations, and is part of the fragment manager's back stack.

	getFragmentManager()
            .beginTransaction()

            // Replace the default fragment animations with animator resources representing
            // rotations when switching to the back of the card, as well as animator
            // resources representing rotations when flipping back to the front (e.g. when
            // the system Back button is pressed).
	    .setCustomAnimations(
	    			 R.animator.card_flip_right_in, R.animator.card_flip_right_out,
	    			 R.animator.card_flip_left_in, R.animator.card_flip_left_out)

            // Replace any fragments currently in the container view with a fragment
            // representing the next page (indicated by the just-incremented currentPage
            // variable).
            .replace(R.id.movie_info_container, new BackFragment())

            // Add this transaction to the back stack, allowing users to press Back
            // to get to the front of the card.
            .addToBackStack(null)

            // Commit the transaction.
            .commit();
    }

}
