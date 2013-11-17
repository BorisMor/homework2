package com.example.homework2;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.*;
import com.android.volley.toolbox.*;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnItemClickListener {

	private ListView lvPlaneta;
	private List<ItemListView> dataListView;
	
	public void loadData()	
	{		
		this.dataListView = new ArrayList<ItemListView>();
		this.dataListView.add(new ItemListView(R.drawable.sun,		"http://goo.gl/4coSsF",	"Солнце", 	"http://ru.wikipedia.org/w/index.php?title=%D0%A1%D0%BE%D0%BB%D0%BD%D1%86%D0%B5&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.mercury,	"http://goo.gl/j7lCg5",	"Меркурий",	"http://ru.wikipedia.org/w/index.php?title=%D0%9C%D0%B5%D1%80%D0%BA%D1%83%D1%80%D0%B8%D0%B9&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.venus,	"http://goo.gl/1LgvWz",	"Венера", 	"http://ru.wikipedia.org/w/index.php?title=%D0%92%D0%B5%D0%BD%D0%B5%D1%80%D0%B0&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.earth,	"http://goo.gl/6XENai",	"Земля", 	"http://ru.wikipedia.org/w/index.php?title=%D0%97%D0%B5%D0%BC%D0%BB%D1%8F&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.mars,		"http://goo.gl/pikxLU",	"Марс", 	"http://ru.wikipedia.org/w/index.php?title=%D0%9C%D0%B0%D1%80%D1%81&printable=yes"));	
		this.dataListView.add(new ItemListView(R.drawable.ceres,	"http://goo.gl/5OJDOq",	"Церера", 	"http://ru.wikipedia.org/w/index.php?title=%D0%A6%D0%B5%D1%80%D0%B5%D1%80%D0%B0&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.jupiter,	"http://goo.gl/drJ2vo",	"Юпитер", 	"http://ru.wikipedia.org/w/index.php?title=%D0%AE%D0%BF%D0%B8%D1%82%D0%B5%D1%80&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.saturn,	"http://goo.gl/CT8YK7",	"Сатурн", 	"http://ru.wikipedia.org/w/index.php?title=%D0%A1%D0%B0%D1%82%D1%83%D1%80%D0%BD&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.uranus,	"http://goo.gl/EowQYC",	"Уран", 	"http://ru.wikipedia.org/w/index.php?title=%D0%A3%D1%80%D0%B0%D0%BD_(%D0%BF%D0%BB%D0%B0%D0%BD%D0%B5%D1%82%D0%B0)&printable=yes"));	
		this.dataListView.add(new ItemListView(R.drawable.neptune,	"http://goo.gl/LTYXiA",	"Нептун", 	"http://ru.wikipedia.org/w/index.php?title=%D0%9D%D0%B5%D0%BF%D1%82%D1%83%D0%BD&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.pluto,	"http://goo.gl/Ux0CzD",	"Плутон", 	"http://ru.wikipedia.org/w/index.php?title=%D0%9F%D0%BB%D1%83%D1%82%D0%BE%D0%BD&printable=yes"));

		
		ItemListViewAdapter dataAdapter = new ItemListViewAdapter(this, this.dataListView);
		this.lvPlaneta.setAdapter(dataAdapter);		
		this.lvPlaneta.setOnItemClickListener(this);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.lvPlaneta = (ListView)findViewById(R.id.lvPlaneta);
		this.loadData();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		if(parent.getId() == R.id.lvPlaneta){
			
			ItemListView item = this.dataListView.get(position);			
			Intent intent = new Intent(this, WikiActivity.class);
			intent.putExtra("url", item.getUrl());
			startActivity(intent);
		}
	}
	
	/**
	 * Пункт для ListView
	 */
	public class ItemListView{
		private int img_res;
		private String text;
		private String url;
		
		private String img_url;	
		private boolean img_url_download; 
		private Bitmap bmp_url;
	
		
		public ItemListView(int _img_res, String _img_url, String _text, String _url){
			this.img_res =	_img_res;
			this.text = 	_text;
			this.url = 		_url;
			this.img_url =	_img_url;
			
			this.img_url_download = false;
		}
		
		public int getImgRes(){
			return this.img_res;
		}
		
		public String getText(){
			return this.text;
		}
		
		public String getUrl(){
			return this.url;
		}
	}
	
	private class ItemListViewAdapter extends BaseAdapter{
		String URL_IMG = "";
		private Activity activity;
		private List<ItemListView> listData;
		
		public ItemListViewAdapter(Activity _activity, List<ItemListView> _listData)
		{
			super();
			this.activity = _activity;
			this.listData = _listData;			
		}	
		
		/**
		 * Загрузка изображений указаных в img_url
		 */
		private void LoadImgWeb(final ItemListView item, final ImageView imageView){
			if(item.img_url_download){
				imageView.setImageBitmap(item.bmp_url) ;
				return;
			}
				
			
			ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(getApplicationContext()), new BitmapLruCache(200));
			imageLoader.get(item.img_url, new ImageLoader.ImageListener() {

				@Override  
				public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {						
						item.bmp_url = imageContainer.getBitmap();
						item.img_url_download = true;
						imageView.setImageBitmap(item.bmp_url) ;									  
				  }

				@Override
				public void onErrorResponse(VolleyError volleyError) {
					  Toast.makeText(getApplicationContext(), "onErrorResponse - \n" + item.img_url, Toast.LENGTH_LONG).show();
				  }			
			});
			
			
		}
		
		@Override
		public int getCount() {
			return this.listData.size();
		}

		@Override
		public Object getItem(int position) {
			return this.listData.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vhElem;
		    
			if( convertView == null ){
		        LayoutInflater inflater = activity.getLayoutInflater();
		        convertView = inflater.inflate(R.layout.item_list, null);
		        
				vhElem = new ViewHolder();			
				vhElem.imgRes =	(ImageView)convertView.findViewById(R.id.imgRes);
				vhElem.text = 	(TextView)convertView.findViewById(R.id.text);
			    convertView.setTag(vhElem);
			    
		    } else {
		    	vhElem = (ViewHolder)convertView.getTag();
		    }		   
			
			// Получаем данные
			ItemListView item = this.listData.get(position);   	
         
			// Помещаем данные 
			this.LoadImgWeb(item, vhElem.imgRes);
		
			if(item.bmp_url == null)
				vhElem.imgRes.setImageResource(item.getImgRes());			
			vhElem.text.setText(item.getText());
		    
			return convertView;
		}
		
	}
	
	static class ViewHolder {
        public ImageView imgRes;
        public TextView text;
    }

}
