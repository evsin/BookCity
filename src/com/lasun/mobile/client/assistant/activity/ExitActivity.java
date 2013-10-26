package com.lasun.mobile.client.assistant.activity;

import android.os.Bundle;
import android.widget.ProgressBar;

public class ExitActivity extends MenuActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress);
		ProgressBar pb = (ProgressBar) findViewById(R.id.test_pb);
		pb.setProgress(20);
	}

	@Override
	protected void onStart() {
		if (getIntent().getBooleanExtra("exit", false)) {
			this.finish();
		}
		super.onStart();
	}
}
