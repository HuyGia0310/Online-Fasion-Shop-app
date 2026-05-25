package ntu.huy.shopapp.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import ntu.huy.shopapp.Domain.BannerModel;
import ntu.huy.shopapp.Domain.CategoryModel;
import ntu.huy.shopapp.Domain.ItemsModel;
import ntu.huy.shopapp.Repository.MainRepository;

public class MainViewModel extends ViewModel {
    private final MainRepository repository=new MainRepository();

    public LiveData<ArrayList<CategoryModel>> loadCategory(){
        return repository.loadCategory();
    }

    public LiveData<ArrayList<BannerModel>> loadBanner(){
        return repository.loadBanner();
    }

    public LiveData<ArrayList<ItemsModel>> loadPopular(){
        return repository.loadPopular();
    }
}
