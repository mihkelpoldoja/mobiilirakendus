package com.andtinder.demo;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.andtinder.model.CardModel;
import com.andtinder.view.CardContainer;
import com.andtinder.view.SimpleCardStackAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    /**
     * This variable is the container that will host our cards
     */
	private CardContainer mCardContainer;
	private ViewFlipper mViewFlipper;
	private Button signUpButton;
	private Button signUpSubmitButton;
	private ArrayList<String> likes;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainlayout);
		mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
		likes = new ArrayList<String>();
		initSignUp();
		initSignIn();
		initCards();
		initMatch();

	}

	private void initMatch() {

	}

	private void initSignUp() {
		signUpSubmitButton = (Button) mViewFlipper.findViewById(R.id.signup).findViewById(R.id.buttonSignUpSubmit);
		signUpSubmitButton.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mViewFlipper.setDisplayedChild(2);
				return false;
			}
		});
	}

	private void initSignIn()
	{
		signUpButton = (Button) mViewFlipper.findViewById(R.id.signin).findViewById(R.id.buttonSignUp);
		signUpButton.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				mViewFlipper.setDisplayedChild(1);
				return false;
			}
		});
	}

	public void initCards()
	{
		mCardContainer = (CardContainer) findViewById(R.id.cards).findViewById(R.id.layoutview);

		Resources r = getResources();

		SimpleCardStackAdapter adapter = new SimpleCardStackAdapter(this);

		CardModel cardModel = new CardModel("Amazon", "Get sidejob on Amazon, earn money as mechanical turk", r.getDrawable(R.drawable.amazon));
		cardModel.setOnClickListener(new CardModel.OnClickListener() {
			@Override
			public void OnClickListener() {

			}
		});

		cardModel.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
			@Override
			public void onLike(CardModel model) {

				mViewFlipper.setDisplayedChild(3);
				likes.add(model.getTitle());
			}

			@Override
			public void onDislike(CardModel model) {
				mViewFlipper.setDisplayedChild(3);
			}
		});
		adapter.add(cardModel);

		adapter.add(CreateCardModel("Intel", "Intel is searching for sales manager", r.getDrawable(R.drawable.intel)));
		adapter.add(CreateCardModel("Code exceptional", "Code expeption is searching for coder", r.getDrawable(R.drawable.codeexc)));
		adapter.add(CreateCardModel("Purcho", "Join Purcho as Q&A engineer", r.getDrawable(R.drawable.purcho)));
		adapter.add(CreateCardModel("Google", "Start your dream career at Google", r.getDrawable(R.drawable.google)));
		adapter.add(CreateCardModel("ITP", "Software Engineer Java - $30K", r.getDrawable(R.drawable.itp)));

		mCardContainer.setAdapter(adapter);
	}

	private CardModel CreateCardModel(String title, String description, Drawable drawble)
	{
		CardModel cardModel = new CardModel(title, description, drawble);
		cardModel.setOnCardDismissedListener(new CardModel.OnCardDismissedListener() {
			@Override
			public void onLike(CardModel model) {
				likes.add(model.getTitle());
			}

			@Override
			public void onDislike(CardModel model) {

			}
		});
		return cardModel;
	}

	public void onViewLikesClick(View v) {
		mViewFlipper.setDisplayedChild(4);
		ListView list = (ListView) findViewById(R.id.likes).findViewById(R.id.listViewLikes);
		ArrayAdapter<String> itemsAdapter =
				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, likes);
		list.setAdapter(itemsAdapter);
	}
}
