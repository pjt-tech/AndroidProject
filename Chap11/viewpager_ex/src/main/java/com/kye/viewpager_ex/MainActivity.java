package com.kye.viewpager_ex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    String[] names = {"AOA","트와이스","여자친구","레드벨벳"};
    int[] id = {R.drawable.jeju1,R.drawable.jeju02,R.drawable.jeju3,R.drawable.jeju4};
    String[] call_Num = {"010-1000-1000","010-2000-2000","010-3000-3000","010-4000-4000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(names.length);
    }

    class  ViewPagerAdapter extends PagerAdapter{
        Context context;

        public ViewPagerAdapter(Context context) {
            this.context = context;
        }

        @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                // 2. ViewPager에서 사용할 뷰 객체를 생성 및 등록
                Person person = new Person(context);
                person.setName(names[position]);
                person.setCall_Btn(call_Num[position]);
                person.setImage(id[position]);
                container.addView(person,position);

                return person;
            }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //4. 화면에 보이지 않는 View 객체를 삭제해서 관리
            container.removeView((View)object);
        }

        @Override
        public int getCount() { //1. 현재 pagerAdapter에서 관리할 페이지의 수
            return names.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            //3. instantiateItem 메소드에 생성한 객체가 뷰페이저와 맞는지 확인하는 메소드

            return view.equals(object);
        }
    }
}
