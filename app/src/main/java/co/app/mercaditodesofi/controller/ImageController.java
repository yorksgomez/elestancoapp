package co.app.mercaditodesofi.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ImageController {

    private static ArrayList<AsyncTask> lookingList = new ArrayList<>();

    public static void getWebImageBitmapUsingCache(String url, ImageView image) {

        AsyncTask<Void, Void, Bitmap> lookForImage = new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... voids) {

                try {
                    URL imageUrl = new URL(url);
                    URLConnection connection = imageUrl.openConnection();
                    connection.setUseCaches(true);
                    Bitmap response = BitmapFactory.decodeStream((InputStream)connection.getContent());

                    return response;
                } catch(Exception ex) {
                    MessageController.showError(ex.toString() + " " + ex.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                image.setImageBitmap(bitmap);
                lookingList.remove(this);
            }

        };

        lookingList.add(lookForImage);
        lookForImage.execute();
    }

    public static void clearLooking() {

        for(AsyncTask task : lookingList) {
            AsyncTask.Status status = task.getStatus();

            if(status.equals(AsyncTask.Status.PENDING))
                task.cancel(false);
            else if(status.equals(AsyncTask.Status.RUNNING))
                task.cancel(true);

        }

        lookingList.clear();

    }

}
