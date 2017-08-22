package nikam.com.mutichoice_listview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button mbook;
    TextView mname;
    String[] movieList;
    boolean[] movieCheck;
    ArrayList<Integer>mlist= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbook=(Button)findViewById(R.id.btnMovieBooking);
        mname=(TextView) findViewById(R.id.txtMovieName);

        movieList=getResources().getStringArray(R.array.Movie_Name);
        movieCheck=new boolean[movieList.length];

        mbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder=new AlertDialog.Builder(MainActivity.this);
               mBuilder.setTitle("Released Movies");

               mBuilder.setMultiChoiceItems(movieList, movieCheck, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked)
                        {
                            if(!mlist.contains(position))
                            {
                                mlist.add(position);
                            }

                            else {
                                mlist.remove(position);
                            }
                        }

                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item="";

                        for(int i=0;i<mlist.size();i++)
                        {
                            item=item+movieList[mlist.get(i)];
                            if(i!=mlist.size()-1)
                            {
                                item=item+",";
                            }
                        }
                      mname.setText(item);
                    }
                });

                mBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i=0;i< movieCheck.length;i++)
                        {
                           movieCheck[i]=false;
                            mlist.clear();
                            mname.setText("");
                        }
                    }
                });

                AlertDialog dialog=mBuilder.create();
                dialog.show();
            }
        });
    }
}
