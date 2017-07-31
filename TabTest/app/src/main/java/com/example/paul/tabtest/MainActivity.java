package com.example.paul.tabtest;

        import android.app.ActionBar;
        import android.app.ActionBar.Tab;
        import android.app.Activity;
        import android.app.TabActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TabHost;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;


public class MainActivity extends TabActivity implements TabHost.OnTabChangeListener {

    private static final String LIST1_TAB_TAG = "List1";
    private static final String LIST2_TAB_TAG = "List2";

    // The two views in our tabbed example
    private ListView listView1;
    private ListView listView2;

    private TabHost tabHost;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = getTabHost();
        tabHost.setOnTabChangedListener(this);

        // setup list view 1
        listView1 = (ListView) findViewById(R.id.list1);

        // create some dummy strings to add to the list
        List<String> list1Strings = new ArrayList<String>();
        list1Strings.add("Item 1");
        list1Strings.add("Item 2");
        list1Strings.add("Item 3");
        list1Strings.add("Item 4");
        listView1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list1Strings));

        listView2 = (ListView) findViewById(R.id.list2);
        List<String> list2Strings = new ArrayList<String>();
        list2Strings.add("fvewgf");
        list2Strings.add("fewfewfvver2");
        list2Strings.add("Itemvnejiovhjiew 3");
        list2Strings.add("Itemidjiewqhd 4");
        listView2.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, list2Strings));
        
        tabHost.addTab(tabHost.newTabSpec(LIST1_TAB_TAG).setIndicator(LIST1_TAB_TAG).setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String arg0) {
                return listView1;
            }
        }));
        tabHost.addTab(tabHost.newTabSpec(LIST2_TAB_TAG).setIndicator(LIST2_TAB_TAG).setContent(new TabHost.TabContentFactory() {
            public View createTabContent(String arg0) {
                return listView2;
            }
        }));

    }

    /**
     * Implement logic here when a tab is selected
     */
    public void onTabChanged(String tabName) {
        if(tabName.equals(LIST2_TAB_TAG)) {
            //do something
        }
        else if(tabName.equals(LIST1_TAB_TAG)) {
            //do something
        }
    }
}