package com.example.mtlapps.popupdemo;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    //ImageButton floatingActionButton;
    PopupWindow popupWindow;
    private View hiddenView;
    int totalViewHeight;


    RelativeLayout mcontentLayout;
    boolean isChecked;

    public boolean onTouchEvent(MotionEvent event) {
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
//                INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        System.out.println("total Layout Height, Commit One More time ";
        if(isChecked) {
            floatingActionButton.setImageResource(R.drawable.info_mcb);
            // floatingActionButton.setBackgroundResource(R.drawable.info_mcb);
            Animation bottomDown = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.slide_down);
            hiddenView.startAnimation(bottomDown);
            hiddenView.setVisibility(View.INVISIBLE);
            // isChecked = !isChecked;
            isChecked = false;
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isChecked = false;
        mcontentLayout = (RelativeLayout) findViewById(R.id.viewA);
        hiddenView = findViewById(R.id.popupview);
        hiddenView.setVisibility(View.INVISIBLE);
       // isChecked = !isChecked;

        floatingActionButton = findViewById(R.id.fab);
        isChecked= false;
        overridePendingTransition(R.anim.slide_down, R.anim.slide_up);
       // final int totalViewHeight; //= viewObject.getHeight();


        final RelativeLayout layout = findViewById(R.id.viewA);

        changeLayoutHeight();
        ViewTreeObserver vto = layout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int width  = layout.getMeasuredWidth();
                int height = layout.getMeasuredHeight();

                totalViewHeight = layout.getMeasuredHeight();

               // changeLayoutHeight(height);
                System.out.println("total Layout Height "+height);


            }
        });


// Changes the height and width to the specified *pixels*
//        params.height = 100;
//        params.width = 100;
//        layout.setLayoutParams(params);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!isChecked) {
                    floatingActionButton.setImageResource(R.drawable.close_mcb);
                   // floatingActionButton.setBackgroundResource(R.drawable.close_mcb);
                    Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.slide_up);

                    // customView.startAnimation(bottomUp);
                    //customView.setVisibility(View.VISIBLE);

                    hiddenView.startAnimation(bottomUp);
                    hiddenView.setVisibility(View.VISIBLE);
                    isChecked =true;

                }
                else {
                    floatingActionButton.setImageResource(R.drawable.info_mcb);
                   // floatingActionButton.setBackgroundResource(R.drawable.info_mcb);
                    Animation bottomDown = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.slide_down);
                    hiddenView.startAnimation(bottomDown);
                    hiddenView.setVisibility(View.INVISIBLE);
                    isChecked = false;


                }

            }
        });
    }

    private void initiatePopupWindow(View v,boolean isChecked) {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.popupwindow, null);



        // Initialize a new instance of popup window
        popupWindow = new PopupWindow(
                customView,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );

       // popupWindow.showAtLocation(mcontentLayout, Gravity.CENTER, 0, 0);

        // Get a reference for the custom view close button



        // Finally, show the popup window at the center location of root relative layout
      //  popupWindow.showAtLocation(mcontentLayout, Gravity.CENTER, 0, 0);

      //  ViewGroup hiddenPanel = (ViewGroup)findViewById(R.id.cp);

        if (isChecked) {
            Animation bottomUp = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.slide_up);

           // customView.startAnimation(bottomUp);
            //customView.setVisibility(View.VISIBLE);

            hiddenView.startAnimation(bottomUp);
            hiddenView.setVisibility(View.VISIBLE);
        }

        else
        {
            Animation bottomDown = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.slide_down);
            hiddenView.startAnimation(bottomDown);
            hiddenView.setVisibility(View.INVISIBLE);

        }

    }


    public void changeLayoutHeight(){
        View viewObject = findViewById(R.id.popupview);
// Gets the layout params that will allow you to resize the layout
      //  ViewGroup.LayoutParams params = layout.getLayoutParams();
        // int totalViewHeight = layout.getMeasuredHeight();


        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density  = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
//        System.out.println("total dp Height "+ outMetrics.heightPixels+" "+density);
//        System.out.println("total pixel Height "+dpHeight);


        int eachLayoutHeight = (int) (dpHeight-56-(3*125))/4;
        System.out.println("total Layout Height "+dpHeight+"eachLayoutHieght"+eachLayoutHeight);


        LinearLayout topLayout = findViewById(R.id.firstlayoutID);
        ViewGroup.LayoutParams topLayoutParams = topLayout.getLayoutParams();
        topLayoutParams.height =eachLayoutHeight* (int)density;
        topLayout.setLayoutParams(topLayoutParams);

        LinearLayout middleOneLayout = findViewById(R.id.secondlayoutID);
        ViewGroup.LayoutParams middleOneLayoutParams = middleOneLayout.getLayoutParams();
        middleOneLayoutParams.height =eachLayoutHeight*(int)density;
        middleOneLayout.setLayoutParams(middleOneLayoutParams);


        LinearLayout middleTwoLayout = findViewById(R.id.thirdlayoutID);
        ViewGroup.LayoutParams middleTwoLayoutParams = middleTwoLayout.getLayoutParams();
        middleTwoLayoutParams.height =eachLayoutHeight*(int)density;
        middleTwoLayout.setLayoutParams(middleTwoLayoutParams);


        LinearLayout bottomLayout = findViewById(R.id.fourthlayoutID);
        ViewGroup.LayoutParams bottomLayoutParams = bottomLayout.getLayoutParams();
        bottomLayoutParams.height =eachLayoutHeight*(int)density;
        bottomLayout.setLayoutParams(bottomLayoutParams);

        }

//    public static int pxToDp(float px)
//    {
//        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
//    }
//
//    public static int dpToPx(float dp)
//    {
//        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
//    }

}


