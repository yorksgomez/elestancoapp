package co.app.mercaditodesofi.ui.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ShareViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ShareViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Puntúa El Estanco en la PlayStore");
    }

    public LiveData<String> getText() {
        return mText;
    }
}