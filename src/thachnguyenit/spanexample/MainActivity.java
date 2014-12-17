package thachnguyenit.spanexample;


import thachnguyenit.spanexample.util.ImageGetterUtil;
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class MainActivity extends Activity {
	
		public static final String TAG ="MainActivity";
		
		String source = "<p>Day la Maria Ozawa<p> <img src=\"https://lh3.googleusercontent.com/-z9LaUdzthig/AAAAAAAAAAI/AAAAAAAAAAA/QBGgVcgfaA4/photo.jpg\""
				+ "<p>Day la Saori Hara<p> <img src=\"https://lh6.googleusercontent.com/-6Oj2tM0qJZ0/AAAAAAAAAAI/AAAAAAAAAAA/I-cYJMrUOfU/photo.jpg\"";
		
		TextView mTextExample;
		//
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			mTextExample  = (TextView)findViewById(R.id.act_main_my_textview_example);	
			ImageGetterUtil getterUtil = new ImageGetterUtil(this,mTextExample);
			mTextExample.setText(Html.fromHtml(source,  getterUtil, null));
			mTextExample.setMovementMethod(LinkMovementMethod.getInstance());
			mTextExample.setText(source);
		}
}
