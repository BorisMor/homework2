package com.example.homework2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;

public class WikiActivity extends Activity implements OnClickListener {
	WebView wvWiki;
	Button butClose;
	ProgressBar progressWeb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wiki);
		
		this.wvWiki = (WebView)findViewById(R.id.wvWiki);
		this.butClose = (Button)findViewById(R.id.butClose);
		this.progressWeb = (ProgressBar)findViewById(R.id.progressWeb);
		
		this.butClose.setOnClickListener(this);
		
		String url = getIntent().getStringExtra("url");		
		wvWiki.setWebViewClient(new WebViewClient()
		{
			@Override
			 public void onPageStarted(WebView view, String url, Bitmap favicon) 
			 {
				super.onPageStarted(view, url, favicon);
				WikiActivity.this.progressWeb.setVisibility(View.VISIBLE);	
			 }
			
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
				WikiActivity.this.progressWeb.setVisibility(View.INVISIBLE);
			}
						
			
		}		
				);
		wvWiki.loadUrl(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wiki, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		
		if(v.getId() == R.id.butClose)
			finish();
		
	}

}
