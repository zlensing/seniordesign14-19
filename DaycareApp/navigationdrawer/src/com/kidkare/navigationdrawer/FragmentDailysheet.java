package com.kidkare.navigationdrawer;

import java.util.ArrayList;

import com.example.navigationdrawer.R;
import com.kidkare.dataaccessobjects.DailySheet;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
 
public class FragmentDailysheet extends Fragment {
	private ViewGroup root;
	private ArrayList<DailySheet> dailySheets = new ArrayList<DailySheet>();
	private String[] dayStrings;
 
    public static Fragment newInstance(Context context) {
    	FragmentDailysheet f = new FragmentDailysheet();
 
        return f;
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        root = (ViewGroup) inflater.inflate(R.layout.fragment_dailysheet, null);
        
        /*
        dailySheets.add(new DailySheet(1, 1, 20141203, "Good", "Yes", "Played well with others",
        		"1Chicken", "2Bun", "3Carrot", "1Apple", "22% Milk", "3Pudding"));
        dailySheets.add(new DailySheet(2, 1, 20141202, "good", "Yes", "played well with others",
        		"1Hamburger", "2Bun", "3Carrot", "1Apple", "22% Milk", "3Juice Box"));
        dailySheets.add(new DailySheet(3, 1, 20141201, "good", "", "", "", "", "", "", "", ""));
        */
        dailySheets = MainActivity.datasource.getSheets(MainActivity.child.getId());
        createDayStrings();
        
        Button b = (Button) root.findViewById(R.id.dailysheetbutton);
        //b.setOnClickListener(new DSButtonOnClickListener());
        
        b.setOnClickListener(new OnClickListener(){

        	@Override
        	public void onClick(View v) {
        		AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        		builder.setTitle("Select Day");
        		builder.setItems(dayStrings, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    	changeSheet(which);
                }
        		});
        		builder.create();
        		builder.show();
        	}
        	
        });
        
        return root;
    }
    
    private void createDayStrings() {
    	dayStrings = new String[dailySheets.size()];
    	String temp;
		for(int i=0;i<dailySheets.size();i++){
			//Date form YYYYMMDD
			//index     01234567
			temp = "" + dailySheets.get(i).getDate();
			switch(Integer.parseInt(temp.substring(4, 6))){
				case 1: dayStrings[i] = "Jan. ";
						break;
				case 2: dayStrings[i] = "Feb. ";
						break;
				case 3: dayStrings[i] = "Mar. ";
						break;
				case 4: dayStrings[i] = "Apr. ";
						break;
				case 5: dayStrings[i] = "May ";
						break;
				case 6: dayStrings[i] = "June ";
						break;
				case 7: dayStrings[i] = "July ";
						break;
				case 8: dayStrings[i] = "Aug. ";
						break;
				case 9: dayStrings[i] = "Sept. ";
						break;
				case 10: dayStrings[i] = "Oct. ";
						break;
				case 11: dayStrings[i] = "Nov. ";
						break;
				case 12: dayStrings[i] = "Dec. ";
						break;
			}
			
			dayStrings[i] += temp.substring(6) + ", " + temp.subSequence(0, 4);
		}
		
	}

	private void changeSheet(int sheet){
		DailySheet day = dailySheets.get(sheet);
		
    	TextView date = (TextView) root.findViewById(R.id.sheetDate);
    	TextView name = (TextView) root.findViewById(R.id.sheetName);
    	TextView attitude = (TextView) root.findViewById(R.id.sheetAttitude);
    	TextView nap = (TextView) root.findViewById(R.id.sheetNap);
    	TextView main = (TextView) root.findViewById(R.id.sheetMain);
    	TextView mainAmount = (TextView) root.findViewById(R.id.MainAmount);
    	TextView carb = (TextView) root.findViewById(R.id.sheetCarb);
    	TextView carbAmount = (TextView) root.findViewById(R.id.CarbAmount);
    	TextView vegi = (TextView) root.findViewById(R.id.sheetVegi);
    	TextView vegiAmount = (TextView) root.findViewById(R.id.VegiAmount);
    	TextView fruit = (TextView) root.findViewById(R.id.sheetFruit);
    	TextView fruitAmount = (TextView) root.findViewById(R.id.FruitAmount);
    	TextView milk = (TextView) root.findViewById(R.id.sheetMilk);
    	TextView milkAmount = (TextView) root.findViewById(R.id.MilkAmount);
    	TextView other = (TextView) root.findViewById(R.id.sheetOther);
    	TextView otherAmount = (TextView) root.findViewById(R.id.OtherAmount);
    	TextView notes = (TextView) root.findViewById(R.id.sheetNotes);
    	
    	date.setText(dayStrings[sheet]);
    	name.setText("" + MainActivity.datasource.getChild(day.getChild()).getName());
    	attitude.setText(day.getAttitude());
    	nap.setText(day.getNap());
    	main.setText(day.getMain().substring(1));
    	mainAmount.setText(getAmountText(day.getMain().charAt(0)));
    	carb.setText(day.getCarb().substring(1));
    	carbAmount.setText(getAmountText(day.getCarb().charAt(0)));
    	vegi.setText(day.getVegi().substring(1));
    	vegiAmount.setText(getAmountText(day.getVegi().charAt(0)));
    	fruit.setText(day.getFruit().substring(1));
    	fruitAmount.setText(getAmountText(day.getFruit().charAt(0)));
    	milk.setText(day.getMilk().substring(1));
    	milkAmount.setText(getAmountText(day.getMilk().charAt(0)));
    	other.setText(day.getOther().substring(1));
    	otherAmount.setText(getAmountText(day.getOther().charAt(0)));
    	notes.setText(day.getNotes());
    	
    }
	
	private String getAmountText(char c){
		String str = "";
		int i = Integer.parseInt("" +c);
		switch(i){
			case 1: str = "All";
					break;
			case 2: str = "Most";
					break;
			case 3: str = "Some";
					break;
			case 4: str = "None";
					break;			
		}
		return str;
	}
 
}

