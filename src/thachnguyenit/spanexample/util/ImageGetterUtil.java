package thachnguyenit.spanexample.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import thachnguyenit.spanexample.MainActivity;
import thachnguyenit.spanexample.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.widget.TextView;


/**
 * The ImageGetterUtil
 * @author ThachNguyen
 * @version 1.0 
 * 			No support for multiscreensize. 
 * */
public class ImageGetterUtil implements ImageGetter { 
	// The context of activity
	Context mContext;
	// The textview control
	TextView mContentText;
	
	/**
	 * The contructor
	 * @param c
	 * 		The context of activity
	 * @param t
	 * 		The textview control
	 */
	public ImageGetterUtil(Context c, TextView t){
		this.mContext = c;
		this.mContentText = t;
	}
	
	/**
	 * @return the context
	 */
	public Context getContext(){
		return mContext;
	}
	
	/** 
	 * @param source
	 * 			the link of tag <img>
	 * @return drawable
	 */
	@Override
	public Drawable getDrawable(String source) {
		// TODO Auto-generated method stub
		
		LevelListDrawable d = new LevelListDrawable();
		Drawable empty = getContext().getResources().getDrawable(R.drawable.ic_launcher);
		 d.addLevel(0, 0, empty);
	     d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());
	     new ImageGetterAsync(mContext,mContentText,d).execute(source);
	     return d;
	}
	

	/**
	 * 
	 * @author ThachNguyen
	 *
	 */
	public static class ImageGetterAsync extends AsyncTask<Object, Void, Bitmap>{

		LevelListDrawable listDrawable;
		Context context;
		TextView textView;
		
		public ImageGetterAsync(Context ctx, TextView tv, LevelListDrawable lv) {
			// TODO Auto-generated constructor stub
			this.context = ctx;
			this.textView = tv;
			this.listDrawable = lv;
		}
		@Override
		protected Bitmap doInBackground(Object... params) {
			// TODO Auto-generated method stub
			String source = (String) params[0];
			
			Bitmap bitmap = null;
			
			try {
				InputStream inputStream = new URL(source).openStream();
				bitmap = BitmapFactory.decodeStream(inputStream);
				return bitmap;
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return bitmap;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null){
				
				
				
				
				listDrawable.addLevel(1,1, new BitmapDrawable(context.getResources(),result));
				listDrawable.setBounds(0, 0,result.getWidth(), result.getHeight());
				listDrawable.setLevel(1);
				
				//textView.invalidate();
				//textView.postInvalidate();
				
				// update view
				CharSequence text = textView.getText();
                textView.setText(text);
               
			}
		}
	}

}
