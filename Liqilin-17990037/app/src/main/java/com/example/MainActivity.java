package com.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.example.liqilin.R;
import com.example.liqilin.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Fragment oneFragment,twoFragment,posFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimaryDark));
//        getSupportActionBar().hide();
        initView();
        initListener();
    }

    private void initView() {
        oneFragment=new IndexFragment();
        twoFragment=new PersonFragment();
        posFragment=new ShopCarFragment();
        setFragment(oneFragment);
    }

    private void initListener() {
        binding.bnv.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId()== R.id.indexFragment){
                getSupportActionBar().setTitle("首页");
                setFragment(oneFragment);
            }else if (item.getItemId()==R.id.shop){
                getSupportActionBar().setTitle("购物车");
                setFragment(posFragment);
            }else {
                getSupportActionBar().setTitle("个人中心");
                setFragment(twoFragment);
            }
            return true;
        });
    }

    private void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container,fragment).commit();
    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                Toast.makeText(this, "Action Search", Toast.LENGTH_SHORT).show();
//                break;
//
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //使用菜单填充器获取menu下的菜单资源文件
//        getMenuInflater().inflate(R.menu.my_bar,menu);
//        //获取搜索的菜单组件
//        MenuItem menuItem = menu.findItem(R.id.search);
//        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//        //设置搜索的事件
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast t = Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT);
//                t.setGravity(Gravity.TOP,0,0);
//                t.show();
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
    public void selectShopCar(){
        getSupportActionBar().setTitle("购物车");
        setFragment(posFragment);
        MenuItem item = binding.bnv.getMenu().getItem(1);
        item.setChecked(true);
    }
}