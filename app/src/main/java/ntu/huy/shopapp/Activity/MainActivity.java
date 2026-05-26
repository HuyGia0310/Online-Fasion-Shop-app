package ntu.huy.shopapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;

import ntu.huy.shopapp.Adapter.CategoryAdapter;
import ntu.huy.shopapp.Adapter.PopularAdapter;
import ntu.huy.shopapp.Adapter.SliderAdapter;
import ntu.huy.shopapp.Domain.BannerModel;
import ntu.huy.shopapp.Domain.CategoryModel;
import ntu.huy.shopapp.R;
import ntu.huy.shopapp.ViewModel.MainViewModel;
import ntu.huy.shopapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new MainViewModel();

        initCategory();
        initSlider();
        initPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        binding.bottomNavigation.setItemSelected(R.id.home,true);
        binding.bottomNavigation.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

            }
        });
    }

    private void initPopular() {
        binding.progressBarPopular.setVisibility(View.VISIBLE);
        viewModel.loadPopular().observeForever(itemsModels -> {
            if(!itemsModels.isEmpty()){
                binding.polularView.setLayoutManager(
                    new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
                binding.polularView.setAdapter(new PopularAdapter(itemsModels));
                binding.polularView.setNestedScrollingEnabled(true);
            }
            binding.progressBarPopular.setVisibility(View.GONE);
        });
        viewModel.loadPopular();
    }

    private void initSlider() {
        binding.progressBarSLider.setVisibility(View.VISIBLE);
        viewModel.loadBanner().observeForever(bannerModels ->{
            if(bannerModels!=null && !bannerModels.isEmpty()){
                banner(bannerModels);
                binding.progressBarSLider.setVisibility(View.GONE);
            }
        } );

        viewModel.loadBanner();
    }

    private void banner(ArrayList<BannerModel> bannerModels) {
        binding.viewPagerSlider.setAdapter(new SliderAdapter(binding.viewPagerSlider,bannerModels));
        binding.viewPagerSlider.setClipToPadding(false);
        binding.viewPagerSlider.setClipChildren(false);
        binding.viewPagerSlider.setOffscreenPageLimit(3);
        binding.viewPagerSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));

        binding.viewPagerSlider.setPageTransformer(compositePageTransformer);
    }

    private void initCategory() {
        binding.progressBarCategory.setVisibility(View.VISIBLE);
        viewModel.loadCategory().observeForever(categoryModels -> {
            binding.CategoryView.setLayoutManager(new LinearLayoutManager(
                    MainActivity.this, LinearLayoutManager.HORIZONTAL,false));
            binding.CategoryView.setAdapter(new CategoryAdapter(categoryModels));
            binding.CategoryView.setNestedScrollingEnabled(true);
            binding.progressBarCategory.setVisibility(View.GONE);
        });
    }
}