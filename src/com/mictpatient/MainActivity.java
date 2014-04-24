package com.mictpatient;

import com.mictpatient.R;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	private FragmentTabHost mTabHost;
	private ActionBar mActionBar;

	private Class<?> fragmentArray[] = { 
			HomeFragment.class, 
			ScoresFragment.class, 
			MessageFragment.class, 
			SurroundFragment.class,
			ContactsFragment.class };

	private int mImageViewArray[] = {
			R.drawable.tab_home_selector,
			R.drawable.tab_scores_selector, 
			R.drawable.tab_message_selector, 
			R.drawable.tab_surround_selector,
			R.drawable.tab_contacts_selector };

	private int mTextviewArray[] = { 
			R.string.tab_home, 
			R.string.tab_scores,
			R.string.tab_messages, 
			R.string.tab_surround,
			R.string.tab_contacts};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mActionBar = getSupportActionBar();
		mTabHost.setup(this, getSupportFragmentManager(), R.id.tabContent);
		int count = fragmentArray.length;

		for (int i = 0; i < count; i++) {
			TabSpec tabSpec = mTabHost.newTabSpec(getString(mTextviewArray[i])).setIndicator(getTabItemView(i));
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tab_bg_selector);
		}
		
		mTabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				changeActionBar(tabId);
			}
		});
	}

	private View getTabItemView(int index) {
		View view = LayoutInflater.from(this).inflate(R.layout.tab_item_view, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);
		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextviewArray[index]);
		return view;
	}
	
	private void changeActionBar(String tabId){
		mActionBar.setTitle(tabId);
	}
}
