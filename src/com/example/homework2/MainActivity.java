package com.example.homework2;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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
		this.dataListView.add(new ItemListView(R.drawable.sun,		"Солнце", "http://ru.wikipedia.org/w/index.php?title=%D0%A1%D0%BE%D0%BB%D0%BD%D1%86%D0%B5&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.mercury,	"Меркурий", "http://ru.wikipedia.org/w/index.php?title=%D0%9C%D0%B5%D1%80%D0%BA%D1%83%D1%80%D0%B8%D0%B9&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.venus,	"Венера", "http://ru.wikipedia.org/w/index.php?title=%D0%92%D0%B5%D0%BD%D0%B5%D1%80%D0%B0&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.earth,	"Земля", "http://ru.wikipedia.org/w/index.php?title=%D0%97%D0%B5%D0%BC%D0%BB%D1%8F&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.mars,		"Марс", "http://ru.wikipedia.org/w/index.php?title=%D0%9C%D0%B0%D1%80%D1%81&printable=yes"));	
		this.dataListView.add(new ItemListView(R.drawable.ceres,	"Церера", "http://ru.wikipedia.org/w/index.php?title=%D0%A6%D0%B5%D1%80%D0%B5%D1%80%D0%B0&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.jupiter,	"Юпитер", "http://ru.wikipedia.org/w/index.php?title=%D0%AE%D0%BF%D0%B8%D1%82%D0%B5%D1%80&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.saturn,	"Сатурн", "http://ru.wikipedia.org/w/index.php?title=%D0%A1%D0%B0%D1%82%D1%83%D1%80%D0%BD&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.uranus,	"Уран", "http://ru.wikipedia.org/w/index.php?title=%D0%A3%D1%80%D0%B0%D0%BD_(%D0%BF%D0%BB%D0%B0%D0%BD%D0%B5%D1%82%D0%B0)&printable=yes"));	
		this.dataListView.add(new ItemListView(R.drawable.neptune,	"Нептун", "http://ru.wikipedia.org/w/index.php?title=%D0%9D%D0%B5%D0%BF%D1%82%D1%83%D0%BD&printable=yes"));
		this.dataListView.add(new ItemListView(R.drawable.pluto,	"Плутон", "http://ru.wikipedia.org/w/index.php?title=%D0%9F%D0%BB%D1%83%D1%82%D0%BE%D0%BD&printable=yes"));

		
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
		
		public ItemListView(int _img_res, String _text, String _url){
			this.img_res = _img_res;
			this.text = _text;
			this.url = _url;
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

		private Activity activity;
		private List<ItemListView> listData;
		
		public ItemListViewAdapter(Activity _activity, List<ItemListView> _listData)
		{
			super();
			this.activity = _activity;
			this.listData = _listData;
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
			View lvElem;
		    
			if( convertView == null ){
		        LayoutInflater inflater = activity.getLayoutInflater();
		        lvElem = inflater.inflate(R.layout.item_list, null);
		    } else {
		    	lvElem = convertView;
		    }
		    
		    ImageView _iv = (ImageView)lvElem.findViewById(R.id.imgRes);
		    TextView _text = (TextView)lvElem.findViewById(R.id.text);
		    
		    ItemListView item = this.listData.get(position);
		    
		     _iv.setImageResource(item.getImgRes());
		     _text.setText(item.getText());
		    
		    return lvElem;
		}
		
	}

}
