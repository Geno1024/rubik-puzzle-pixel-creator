package com.geno.rubikpuzzle;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.widget.LinearLayout.*;
import android.graphics.*;
import android.view.*;

public class MainActivity extends Activity 
{
	public LinearLayout l;
	public int width, height;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		l = (LinearLayout) findViewById(R.id.maintable);
		
		LinearLayout menu = new LinearLayout(this);
		LayoutParams p = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		int mgn = 16;
		p.setMargins(mgn, mgn, mgn, mgn);
		menu.setLayoutParams(p);
		menu.setOrientation(LinearLayout.VERTICAL);
		TextView w = new TextView(this);
		w.setText("Width: ");
		menu.addView(w, p);
		final EditText wid = new EditText(this);
		menu.addView(wid, p);
		TextView h = new TextView(this);
		h.setText("Height: ");
		menu.addView(h, p);
		final EditText hei = new EditText(this);
		menu.addView(hei, p);
		
		AlertDialog.Builder ab = new AlertDialog.Builder(this)
			.setTitle("Rubik Puzzle Pixel Creator")
			.setView(menu)
			.setPositiveButton("OK", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					try
					{
						width = Integer.parseInt(wid.getText().toString());
						height = Integer.parseInt(hei.getText().toString());
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
					main();
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					finish();
				}
			});
		ab.show();
	}

	public void main()
	{
		LinearLayout ruler = new LinearLayout(this);
		TextView st = new TextView(this);
		st.setWidth(64);
		st.setHeight(64);
		st.setTextSize(10);
		ruler.addView(st);
		for (int j = 0; j < 3 * width; j++)
		{
			TextView t = new TextView(this);
			t.setWidth(64);
			t.setHeight(64);
			t.setTextSize(10);
			t.setText(j / 3 + "." + (j % 3 + 1));
			t.setTextColor((j / 3) % 2 == 0 ? 0xFF8080FF : 0xFFFF8080);
			t.setGravity(Gravity.CENTER);
			t.setTypeface(Typeface.MONOSPACE);
			ruler.addView(t);
		}
		l.addView(ruler);
		for (int i = 0; i < 3 * height; i++)
		{
			LinearLayout horiz = new LinearLayout(this);
			TextView t = new TextView(this);
			t.setWidth(64);
			t.setHeight(64);
			t.setTextSize(10);
			t.setText(i / 3 + "." + (i % 3 + 1));
			t.setTextColor((i / 3) % 2 == 0 ? 0xFFFF8080 : 0xFF8080FF);
			t.setGravity(Gravity.CENTER);
			t.setTypeface(Typeface.MONOSPACE);
			horiz.addView(t);
			for (int j = 0; j < 3 * width; j++)
			{
				final LinearLayout vert = new LinearLayout(this);
				LayoutParams p = new LayoutParams(64, 64);
				vert.setLayoutParams(p);
				vert.setBackgroundColor(getColorBox(0));
				vert.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View p1)
						{
							AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this)
								.setItems(new String[]{"White", "Yellow", "Green", "Blue", "Orange", "Red"}, new DialogInterface.OnClickListener()
								{
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										vert.setBackgroundColor(getColorBox(p2));
									}
								});
							ab.show();
						}
				});
				horiz.addView(vert);
			}
			l.addView(horiz);
		}
	}

	public int getColorBox(int i)
	{
		if (i < 1) return Color.WHITE;
		else if (i < 2) return Color.YELLOW;
		else if (i < 3) return Color.GREEN;
		else if (i < 4) return Color.BLUE;
		else if (i < 5) return 0xFFFFA500;
		else return Color.RED;
	}
}
